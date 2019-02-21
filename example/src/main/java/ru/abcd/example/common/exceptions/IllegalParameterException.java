package ru.abcd.example.exceptions;


/**
 * Исключение генерится при передаче в метод неверных параметров
 * 
 * @author dmitry
 *
 */
public class IllegalParameterException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2915995289186667970L;

	public IllegalParameterException(String message, int exceptionsCode) {
		super(message, exceptionsCode);
	}

}
