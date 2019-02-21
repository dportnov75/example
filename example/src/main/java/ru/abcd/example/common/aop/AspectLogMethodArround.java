package ru.abcd.example.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Класс определяет реализацию аспекта, привязанного к методу аннотированному
 * {@link ru.abcd.example.common.aop.AnnotationLogMethodArround}
 * 
 * @author dmitry
 *
 */
@Aspect
@Service
public class AspectLogMethodArround {

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
	public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.info("Начало выполнения метода: " + joinPoint.getSignature().getName());
		final long startTime = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		final long endTime = System.currentTimeMillis();
		logger.info("Окончание выполнения метода: " + joinPoint.getSignature().getName()
				+ " Врямя выполнения метода в милисекундах = " + (endTime - startTime));
		return proceed;
	}
}
