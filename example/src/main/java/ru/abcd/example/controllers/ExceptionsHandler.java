package ru.abcd.example.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import ru.abcd.example.common.exceptions.BaseException;

/**
 * Единый обработчик исключений в слое фасадов
 * 
 * @author dmitry
 *
 */
@RestControllerAdvice(basePackages = { "ru.abcd.example.controllers" })
@Slf4j
public class ExceptionsHandler {

	/**
	 * Обработка пользовательских исключений, т.е. всех, которые произошли от
	 * {@link ru.abcd.example.common.exceptions.BaseException}
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BaseException.class)
	public ResponseError handleCustom(HttpServletRequest request, BaseException ex) {
		log.error("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
		return ResponseError.builder().сode(ex.getExceptionCode()).message(ex.getMessage())
				.url(request.getRequestURI()).build();
	}

	/**
	 * Обработка всех неизвестных исключений
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseError handleUnknown(HttpServletRequest request, Exception ex) {
		log.error("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
		return ResponseError.builder().сode(-1).message("Неизвестное исключение. Сообщение: " + ex.getMessage())
				.url(request.getRequestURI()).build();
	}
}
