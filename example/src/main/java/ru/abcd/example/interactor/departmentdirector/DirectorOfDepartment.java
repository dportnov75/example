package ru.abcd.example.interactor.departmentdirector;

import java.util.Collection;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.interactor.School;
import ru.abcd.example.interactor.Teacher;

/**
 * Интерфейс реализации функционала, свойственного директору департамента образования
 * @author dmitry
 *
 */
public interface DirectorOfDepartment {

	/**
	 * Создает новую школу
	 * @param number Номер школы
	 * @return Школа
	 * @throws IllegalParameterException Номер выходит за границы допустимого
	 * @throws CreateException Не удалось создать
	 */
	public School createSchool(int number) throws IllegalParameterException, CreateException;
	
	/**
	 * Создает учителя
	 * @param teacher Новый учитель
	 * @return учитель
	 * @throws IllegalParameterException не задан учитель, либо его поля не прошли проверку
	 * @throws CreateException Не удалось создать
	 */
	public Teacher createTeacher(Teacher teacher)throws IllegalParameterException, CreateException;
	
	
	/**
	 * Метод постраницно возвращает все школы
	 * @param page Страница
	 * @param recordCount Кол-во записей на странице
	 * @return Список всех школ
	 * @throws IllegalParameterException Отрицательное значение любого параметра
	 */
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException;
	
}
