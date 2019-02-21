package ru.abcd.example.interactor;

import java.util.Optional;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.UpdateException;

/**
 * Интерфейс доступа к сущностям {@link ru.abcd.example.interactor.Person}
 * @author dmitry
 *
 * @param <Dto> Модель конкретной персоны
 */
public interface PersonAdapter<Dto extends Person> {

	/**
	 * Метод возвращает персону по идентификатору
	 * @param id Идентификатор
	 * @return Персоны, или {@literal Optional#empty(), если нет
	 */
	public Optional<Dto> findById(int id);
	
	/**
	 * Добавляет новую персону в систему
	 * @param person Персона
	 * @return Персона с идентификатором
	 * @throws IllegalParameterException Персона {@code null }, или содержит неверные поля
	 * @throws CreateException Не удалось создать
	 */
	public Dto add(Dto person) throws IllegalParameterException, CreateException;
	
	/**
	 * Редактирует персону
	 * @param person Персона
	 * @throws IllegalParameterException  Персона {@code null }, или содержит неверные поля
	 * @throws UpdateException Не удалось сохранить
	 */
	public void update(Dto person) throws IllegalParameterException, UpdateException;
	
}
