package ru.abcd.example.interactor;

import java.util.Collection;
import java.util.Optional;

import ru.abcd.example.exceptions.CreateException;
import ru.abcd.example.exceptions.IllegalParameterException;
import ru.abcd.example.interactor.dto.School;
import ru.abcd.example.interactor.dto.Teacher;

/**
 * Интерфейс доступа к сущностям 
 * {@link ru.abcd.example.interactor.dto.School}
 * 
 * @author dmitry
 *
 */
public interface SchoolAdapter {

	/**
	 * Метод возращает модель школы по идентификатору
	 * 
	 * @param id идентификатор
	 * @return Модель школы, если нет, то {@literal Optional#empty()}
	 */
	public Optional<School> findById(int id);

	/**
	 * Метод создает новую школу
	 * 
	 * @param number Номер школы
	 * @return Модель созданной школы
	 * @throws IllegalParameterException Неверный номер школы
	 * @throws CreateException           Не удалось сохранить в репозитории
	 */
	public School add(int number) throws IllegalParameterException, CreateException;
	
	/**
	 * Метод возвращает коллекцию учителей школы
	 * @param number
	 * @return
	 */
	public Collection<Teacher> getSchollTeachers(int number); 
	
	/**
	 * Метод постраницно возвращает все школы
	 * @param page Страница
	 * @param recordCount Кол-во записей на странице
	 * @return Список всех школ
	 * @throws IllegalParameterException Отрицательное значение любого параметра
	 */
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException ;
	
}
