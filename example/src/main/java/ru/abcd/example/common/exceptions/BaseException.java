/**
 * 
 */
package ru.abcd.example.common.exceptions;

/**
 * Базовое исключение. Дополняет полем
 * {@literal BaseException#getExceptionCode()}, в котором содержится код ошибки
 * 
 * @author dmitry
 *
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 343632584956539696L;

	private int exceptionCode;

	/**
	 * Метод возвращает код исключения
	 * 
	 * @return Код исключения
	 */
	public int getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * Конструктор экземпляра
	 * 
	 * @param message        Сообщение
	 * @param exceptionsCode Код исключения
	 * @param cause          Базовое исключение
	 */
	public BaseException(String message, int exceptionsCode, Throwable cause) {
		super(message, cause);
		this.exceptionCode = exceptionsCode;
	}

	/**
	 * Конструктор экземпляра
	 * 
	 * @param message        Сообщение
	 * @param exceptionsCode Код исключения
	 */
	public BaseException(String message, int exceptionsCode) {
		super(message);
		this.exceptionCode = exceptionsCode;
	}

}
