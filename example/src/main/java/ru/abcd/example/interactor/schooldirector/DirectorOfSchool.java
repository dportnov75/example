package ru.abcd.example.interactor.schooldirector;

import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.Teacher;

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
	 * @param id       Идентификатор учителя
	 * @throws UpdateException Невозможно выполнить операцию
	 */
	public void dismissTeacher(int schoolNumber, int id) throws UpdateException;

	/**
	 * Принимает на работу нового учителя
	 * 
	 * @param schoolNumber Номер школы
	 * @param teacher  Учитель
	 * @throws IllegalParameterException Учитель - {@code null}
	 * @throws UpdateException           Невозможно выролнить операцию
	 */
	public void recruitTeacher(int schoolNumber, Teacher teacher) throws IllegalParameterException, UpdateException;
}
