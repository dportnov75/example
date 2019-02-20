package ru.abcd.example.interactor;

import java.util.Optional;

import ru.abcd.example.exceptions.CreateException;
import ru.abcd.example.exceptions.IllegalParameterException;
import ru.abcd.example.exceptions.UpdateException;
import ru.abcd.example.interactor.dto.Person;

/**
 * Интерфейс доступа к сущностям {@link ru.abcd.example.interactor.dto.Person}
 * @author dmitry
 *
 */
interface PersonAdapter<T extends Person> {

	/**
	 * Метод возвращает персону по идентификатору
	 * @param id Идентификатор
	 * @return Персоны, или {@literal Optional#empty(), если нет
	 */
	public Optional<T> findById(int id);
	
	/**
	 * Добавляет новую персону в систему
	 * @param person Персона
	 * @return Персона с идентификатором
	 * @throws IllegalParameterException Персона {@code null }, или содержит неверные поля
	 * @throws CreateException Не удалось создать
	 */
	public T add(T person) throws IllegalParameterException, CreateException;
	
	/**
	 * Редактирует персону
	 * @param person Персона
	 * @throws IllegalParameterException  Персона {@code null }, или содержит неверные поля
	 * @throws UpdateException Не удалось сохранить
	 */
	public void update(T person) throws IllegalParameterException, UpdateException;
}
