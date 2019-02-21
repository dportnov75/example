package ru.abcd.example.common.exceptions;

/**
 * Исключение генерится при неудачном обновлении сущности
 * 
 * @author dmitry
 *
 */
public class UpdateException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4319603213174034265L;

	public UpdateException(String message, int exceptionsCode) {
		super(message, exceptionsCode);
	}

	public UpdateException(String message, int exceptionsCode, Throwable cause) {
		super(message, exceptionsCode, cause);
		
	}
}
