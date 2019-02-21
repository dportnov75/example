/**
 * 
 */
package ru.abcd.example.common.exceptions;


/**
 * Исключение генерится при неудачном создании сущности
 * 
 * @author dmitry
 *
 */
public class CreateException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -741561322115878850L;

	
	
	public CreateException(String message, int exceptionsCode, Throwable cause) {
		super(message, exceptionsCode, cause);
	}



	public CreateException(String message, int exceptionsCode) {
		super(message, exceptionsCode);
	}
	
}
