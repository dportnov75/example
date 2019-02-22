package ru.abcd.example.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;


/**
 * Аннотация для классов декораторов
 * 
 * @author dmitry
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Decorator {
	
	/**
	 * Имя bean-а
	 * @return
	 */
	String  value() default "";
}
