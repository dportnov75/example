package ru.abcd.example.common.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ru.abcd.example.common.exceptions.BaseException;

/**
 * Используется для перехвата исключений, кроме
 * {@link ru.abcd.example.common.exceptions.BaseException}, в методах и
 * генерации пользовательского исключения, производного от
 * {@link ru.abcd.example.common.exceptions.BaseException}
 * 
 * @author dmitry
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AnnotationCatchUnhandledException {

	/**
	 * Класс исключения, экземпляр которого надо создать
	 * 
	 * @return
	 */
	Class<? extends BaseException> value() default BaseException.class;

	/**
	 * Код, см
	 * {@link ru.abcd.example.common.exceptions.BaseException#getExceptionCode()}
	 * 
	 * @return Код
	 */
	int code() default -1;

	/**
	 * Сообщение
	 * 
	 * @return Сообщение
	 */
	String message() default "Ошибка при выполнении метода-";
}
