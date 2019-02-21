package ru.abcd.example.common.exceptions;

import java.lang.reflect.InvocationTargetException;


/**
 * Класс предоставляет методы для проверки условий
 * 
 * @author dmitry
 *
 */
public final class Precondition {

	private Precondition() {

	}

	/**
	 * Метод проверяет условие и в зависимости от параметра conditionTrueThanThrow
	 * гененерится исключение заданного типа<br>
	 * Если не удалось создать исключение заданного типа, то генерится исключение
	 * {@link BaseException}
	 * 
	 * @param condition              Условие ( например что-то == null)
	 * @param errorMessage           Собщение исключения
	 * @param exceptionCode          Код исключения {@link ExceptionCodes}
	 * @param exceptionClass         Класс исключения которое надо сгенерить
	 * @param conditionTrueThanThrow True, то надо генерировать исключение, если
	 *                               условие правильно.<br>
	 *                               False, то надо генерировать исключение только
	 *                               когда условие не выполнилось
	 * @throws T Тип класса исключения наследник {@link BaseException}, или, если не
	 *           удалось создать экземпляр нужного типа, то сам
	 *           {@link BaseException}
	 */
	private static <T extends BaseException> void checkCondition(boolean condition, final String errorMessage,
			int exceptionCode, final Class<T> exceptionClass, boolean conditionTrueThanThrow) throws T {
		if ((conditionTrueThanThrow && condition) || (!conditionTrueThanThrow && !condition)) {
			try {
				T exception = exceptionClass.getConstructor(String.class, int.class).newInstance(errorMessage,
						exceptionCode);
				throw exception;
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				// Не удалось создать экземпляр требуемого исключения. Генерим стандартное
				throw new BaseException(
						"Не удалось создать требуемый экземпляр исключения. Генерим базовое. " + errorMessage,
						exceptionCode);
			}
		}
	}

	/**
	 * Метод проверяет условие и если оно НЕ ВЕРНО, то гененерится исключение
	 * заданного типа<br>
	 * Если не удалось создать исключение заданного типа, то генерится исключение
	 * {@link BaseException}
	 * 
	 * @param condition      Условие ( например что-то != null)
	 * @param errorMessage   Собщение исключения
	 * @param exceptionCode  Код исключения
	 * @param exceptionClass Класс исключения которое надо сгенерить
	 * @throws T Тип класса исключения наследник {@link BaseException}, или, если не
	 *           удалось создать экземпляр нужного типа, то сам
	 *           {@link BaseException}
	 */
	public static <T extends BaseException> void ifFalseThrow(final boolean condition, final String errorMessage,
			final int exceptionCode, final Class<T> exceptionClass) throws T {
		checkCondition(condition, errorMessage, exceptionCode, exceptionClass, false);
	}

	/**
	 * Метод проверяет условие и если ВЕРНО, то гененерится исключение заданного
	 * типа<br>
	 * Если не удалось создать исключение заданного типа, то генерится исключение
	 * {@link BaseException}
	 * 
	 * @param condition      Условие ( например что-то == null)
	 * @param errorMessage   Собщение исключения
	 * @param exceptionCode  Код исключения
	 * @param exceptionClass Класс исключения которое надо сгенерить
	 * @throws T Тип класса исключения наследник {@link BaseException}, или, если не
	 *           удалось создать экземпляр нужного типа, то сам
	 *           {@link BaseException}
	 */
	public static <T extends BaseException> void ifTrueThrow(final boolean condition, final String errorMessage,
			final int exceptionCode, final Class<T> exceptionClass) throws T {
		checkCondition(condition, errorMessage, exceptionCode, exceptionClass, true);
	}
}
