package ru.abcd.example.interactor;

import java.util.Collection;
import java.util.Optional;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.IllegalParameterException;

/**
 * Интерфейс доступа к сущностям 
 * {@link ru.abcd.example.interactor.School}
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
	public Optional<School> findByNumber(int id);

	/**
	 * Метод создает новую школу
	 * 
	 * @param school Школа
	 * @throws IllegalParameterException Неверный номер школы
	 * @throws CreateException           Не удалось сохранить в репозитории
	 */
	public School add(School school) throws IllegalParameterException, CreateException;
	
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
	
	/**
	 * метод удаляет учителя из школы
	 * @param schoolNumber Номер школы
	 * @param teacherId Идентификатор учителя
	 */
	public void deleteTeacher(int schoolNumber, int teacherId);
	
	/**
	 * Метод добавляет учителя в школу
	 * @param schoolNumber Номер школы
	 * @param teacherId Идентификатор учителя
	 */
	public void addTeacher(int schoolNumber, int teacherId);
}
