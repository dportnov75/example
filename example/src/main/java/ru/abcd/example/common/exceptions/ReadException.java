package ru.abcd.example.common.exceptions;

/**
 * Ошибка чтения
 * 
 * @author dmitry
 *
 */
public class ReadException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4104412886480627264L;

	public ReadException(String message, int exceptionsCode, Throwable cause) {
		super(message, exceptionsCode, cause);
	}

	public ReadException(String message, int exceptionsCode) {
		super(message, exceptionsCode);
	}

}
