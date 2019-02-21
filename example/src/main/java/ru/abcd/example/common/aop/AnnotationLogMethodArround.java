package ru.abcd.example.common.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Позволяет логгировать начало и конец выполнения метода
 * 
 * @author dmitry
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AnnotationLogMethodArround {

}
