package ru.abcd.example.common.aop;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.lang.annotation.Annotation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.abcd.example.common.exceptions.BaseException;
import ru.abcd.example.common.exceptions.CreateException;

public class AspectBasedOnAnnotationTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Test
	public void test() {
		exception.expect(CreateException.class);
		exception.expect(hasProperty("exceptionCode", is(100)));
		exception.expectMessage("m");
		AspectBasedOnAnnotation aspect = new AspectBasedOnAnnotation();
		IllegalArgumentException ex = new IllegalArgumentException("s");
		AnnotationCatchException annotation = new AnnotationCatchException() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return AnnotationCatchException.class;
			}
			
			@Override
			public Class<? extends BaseException> value() {
				return CreateException.class;
			}
			
			@Override
			public String message() {
				return "m";
			}
			
			@Override
			public int code() {
				return 100;
			}
		};
		aspect.pointCutAfterThrowing(ex, annotation);
	}
	
	class BadException extends BaseException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private BadException(String message, int exceptionsCode) {
			super(message, exceptionsCode);
		}
		
	}
	
	@Test
	public void test_fault_create_instance() {
		exception.expect(BaseException.class);
		exception.expect(hasProperty("exceptionCode", is(9)));
		exception.expectMessage("a");
		
		AspectBasedOnAnnotation aspect = new AspectBasedOnAnnotation();
		IllegalArgumentException ex = new IllegalArgumentException("s");
		AnnotationCatchException annotation = new AnnotationCatchException() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return AnnotationCatchException.class;
			}
			
			@Override
			public Class<? extends BaseException> value() {
				return BadException.class;
			}
			
			@Override
			public String message() {
				return "a";
			}
			
			@Override
			public int code() {
				return 9;
			}
		};
		aspect.pointCutAfterThrowing(ex, annotation);
	}
}
