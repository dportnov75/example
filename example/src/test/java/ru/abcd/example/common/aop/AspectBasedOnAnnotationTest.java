package ru.abcd.example.common.aop;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.lang.annotation.Annotation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.abcd.example.common.exceptions.BaseException;
import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.UpdateException;

/**
 * Тест аспектов
 * 
 * @author dmitry
 *
 */
public class AspectBasedOnAnnotationTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Создает экземпляр аннотации
	 * @param code Код
	 * @param message сообщение
	 * @param clazz Класс исключения
	 * @return
	 */
	private AnnotationCatchUnhandledException createAnnotation(int code, String message, Class<? extends BaseException> clazz) {
		return new AnnotationCatchUnhandledException() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return AnnotationCatchUnhandledException.class;
			}

			@Override
			public Class<? extends BaseException> value() {
				return clazz;
			}

			@Override
			public String message() {
				return message;
			}

			@Override
			public int code() {
				return code;
			}
		};
	}
	
	/**
	 * Тест хорошего сценария
	 * @throws Throwable 
	 */
	@Test
	public void test() throws Throwable {
		exception.expect(CreateException.class);
		exception.expect(hasProperty("exceptionCode", is(100)));
		exception.expectMessage("m");
		AspectBasedOnAnnotation aspect = new AspectBasedOnAnnotation();
		IllegalArgumentException ex = new IllegalArgumentException("s");
		aspect.pointCutAfterThrowing(ex, createAnnotation(100, "m", CreateException.class));
	}

	/**
	 * Если перхватили {@link BaseException}, то его не должны заменить
	 * @throws Throwable 
	 */
	@Test
	public void test_catch_base_exception() throws Throwable {
		exception.expect(UpdateException.class);
		exception.expect(hasProperty("exceptionCode", is(55)));
		exception.expectMessage("g");
		AspectBasedOnAnnotation aspect = new AspectBasedOnAnnotation();
		UpdateException ex = new UpdateException("g", 55);
		aspect.pointCutAfterThrowing(ex, createAnnotation(1, "hhh", CreateException.class));	
	}

	class BadException extends BaseException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private BadException(String message, int exceptionsCode) {
			super(message, exceptionsCode);
		}

	}

	/**
	 * Если в аннотации прописали класс исключения, экземпляр которого невозможно
	 * создать
	 * @throws Throwable 
	 */
	@Test
	public void test_fault_create_instance() throws Throwable {
		exception.expect(BaseException.class);
		exception.expect(hasProperty("exceptionCode", is(9)));
		exception.expectMessage("a");

		AspectBasedOnAnnotation aspect = new AspectBasedOnAnnotation();
		IllegalArgumentException ex = new IllegalArgumentException("s");
		aspect.pointCutAfterThrowing(ex, createAnnotation(9, "a", BadException.class));
	}
}
