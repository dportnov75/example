package ru.abcd.example.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import brave.SpanCustomizer;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.abcd.example.SpringCloudConfiguration;

/**
 * Сервис отсылает сообщение через Spring Cloud Stream.<br>
 * Загружается, если загружена конфигурация
 * {@link ru.abcd.example.SpringCloudConfiguration}
 * 
 * @author dmitry
 *
 */
@ConditionalOnBean(SpringCloudConfiguration.class)
@Service
@Slf4j
public class CloudStreamPublisher {

	/**
	 * Бин, с помощью которого можно добавить информацию в Sleuth и Zipkin<br>
	 * Например для поиска
	 */
	@Autowired
	private SpanCustomizer span;

	/**
	 * Представление события об увольнении учителя для отправки в AMQP
	 * 
	 * @author dmitry
	 *
	 */
	@Value
	@SuperBuilder
	@ToString
	private static class EventRepresentation {

		/**
		 * идентификатор
		 */
		int id;

		/**
		 * Создает представление события, которое отпарвляется в AMQP
		 * 
		 * @param evt Событие
		 * @return Представление
		 */
		public static EventRepresentation create(TeacherDismissEvent evt) {
			return EventRepresentation.builder().id(evt.getId()).build();
		}
	}

	@Autowired
	private Source messageChannel;

	/**
	 * Обработчик события об увольнении учителя
	 * 
	 * @param evt Событие
	 */
	@EventListener
	public void onTeacherDismiss(TeacherDismissEvent evt) {
		//Метки для 
		span.tag("cloud.stream.start", "старт");
		EventRepresentation evtRep = EventRepresentation.create(evt);
		messageChannel.output().send(MessageBuilder.withPayload(evtRep).build());
		span.tag("cloud.stream.finished", "стоп");
		span.annotate("Событие послано");
		log.info(String.format("Отослано событие - %s. Класс  - %s", evt, evt.getClass().getCanonicalName()));
	}
}
