package ru.abcd.example.interactor.schooldirector;

import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.UpdateException;

/**
 * Интерфейс реализации функционала, свойственного директорам школ
 * 
 * @author dmitry
 *
 */
public interface DirectorOfSchool {

	/**
	 * Уволняет учителя
	 * 
	 * @param schoolNumber Номер школы
	 * @param id           Идентификатор учителя
	 * @throws UpdateException Невозможно выполнить операцию
	 */
	public void dismissTeacher(int schoolNumber, int id) throws IllegalParameterException, UpdateException;

	/**
	 * Принимает на работу нового учителя
	 * 
	 * @param schoolNumber Номер школы
	 * @param teacherId    Идентификатор учителя
	 * @throws IllegalParameterException Учитель - {@code null}
	 * @throws UpdateException           Невозможно выролнить операцию
	 */
	public void recruitTeacher(int schoolNumber, int teacherId) throws IllegalParameterException, UpdateException;
}
