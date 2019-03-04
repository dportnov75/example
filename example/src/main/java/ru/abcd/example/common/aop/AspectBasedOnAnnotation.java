package ru.abcd.example.common.aop;

import java.lang.reflect.InvocationTargetException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.exceptions.BaseException;

/**
 * Класс определяет реализацию аспекта, привязанного к методу аннотированному
 * {@link ru.abcd.example.common.aop.AnnotationLogMethodArround}
 * 
 * @author dmitry
 *
 */
@Aspect
@Service
class AspectBasedOnAnnotation {

	/**
	 * Точка среза вокруг методов, помеченных аннотацией
	 * {@link ru.abcd.example.common.aop.AnnotationLogMethodArround}. Выполняет
	 * логгирование времени выполнения метода
	 * 
	 * @param joinPoint Точка среза
	 * @return То, что возвращает метод в точке среза
	 * @throws Throwable
	 */
	@Around("@annotation(AnnotationLogMethodArround)")
	public Object pointCutArround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.info("Начало выполнения метода: " + joinPoint.getSignature().getName());
		final long startTime = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		final long endTime = System.currentTimeMillis();
		logger.info("Окончание выполнения метода: " + joinPoint.getSignature().getName()
				+ " Врямя выполнения метода в милисекундах = " + (endTime - startTime));
		return proceed;
	}

	/**
	 * Точка среза для методов, которые отмечены аннотацией
	 * {@link AnnotationCatchUnhandledException}
	 * 
	 * @param a Аннотация
	 */
	@Pointcut("@annotation(a)")
	private void pointCut(AnnotationCatchUnhandledException a) {
	}

	/**
	 * Будет вызван после генерации исключения в точке среза
	 * {@link #pointCut(AnnotationCatchUnhandledException)}. <br>
	 * Метод заменит полученное исключение на экземпляр {@link BaseException}, класс
	 * которого прописан в параметре аннотации
	 * {@link AnnotationCatchUnhandledException#value()}
	 * 
	 * @param e          Исключение
	 * @param annotation Экземпляр аннотации
	 * @throws Throwable
	 */
	@AfterThrowing(pointcut = "pointCut(annotation)", throwing = "e")
	public void pointCutAfterThrowing(Throwable e, AnnotationCatchUnhandledException annotation) throws Throwable {
		if (!(e instanceof BaseException)) {
			// подменяем объект исключения
			try {
				throw annotation.value().getConstructor(String.class, int.class, Throwable.class)
						.newInstance(annotation.message(), annotation.code(), e);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				//log.warn("Ошибка при создании экземпляра исключения. Заданный класс - " + annotation.value(), ex);
				throw new BaseException("Не удалось создать требуемый экземпляр исключения. Генерим базовое. "
						+ annotation.message() + ". Сообщение - " + e.getMessage(), annotation.code(), e);
			}

		}
		else throw e;
	}

}
