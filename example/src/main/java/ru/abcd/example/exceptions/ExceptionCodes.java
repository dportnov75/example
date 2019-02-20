/**
 * 
 */
package ru.abcd.example.exceptions;

/**
 * Класс содержит базовые коды исключений
 * 
 * @author dmitry
 *
 */
public class ExceptionCodes {

	/**
	 * Неверный параметр
	 */
	public static final int INCORRECT_PARAMETER = 100;

	/**
	 * Объект уже есть в системе
	 */
	public static final int OBJECT_IS_ALREADY_PRESENT = 200;

	/**
	 * Объект не найден
	 */
	public static final int OBJECT_NOT_FOUND = 201;

	/**
	 * Ошибка при сохранении в репозитории
	 */
	public static final int REPOSITORY_SAVE_ERROR = 300;

}
