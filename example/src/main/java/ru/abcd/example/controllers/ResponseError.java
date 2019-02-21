package ru.abcd.example.controllers;

import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Класс описывает типизированное описание ошибки, которое передается клиенту,
 * после вызова метода любого контроллера, в котором происходит генерация исключения
 * 
 * @author dmitry
 *
 */
@SuperBuilder
@Value
public class ResponseError {

	/**
	 * Код ошибки
	 */
	int сode;
	
	/**
	 * Сообщение
	 */
	String message;

	/**
	 * URL метода, в котором произошло исключение
	 */
	String url;
}
