package ru.abcd.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Spring Cloud Stream. Загружается только если прописано
 * подключение к Rabbit
 * 
 * @author dmitry
 *
 */
@ConditionalOnProperty(prefix = "spring.rabbitmq", name= "host")
@Configuration
@EnableBinding({Source.class})
public class SpringCloudConfiguration {

}
