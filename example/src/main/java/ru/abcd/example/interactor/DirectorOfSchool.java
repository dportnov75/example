package ru.abcd.example.interactor;

import ru.abcd.example.exceptions.IllegalParameterException;
import ru.abcd.example.exceptions.UpdateException;
import ru.abcd.example.interactor.dto.Teacher;

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
	 * @throws UpdateException Невозможно выролнить операцию
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
