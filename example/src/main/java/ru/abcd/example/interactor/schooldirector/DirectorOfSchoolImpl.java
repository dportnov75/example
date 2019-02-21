package ru.abcd.example.interactor.schooldirector;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.SchoolAdapter;
import ru.abcd.example.interactor.Teacher;

/**
 * Реализация функционала акторов типа 'директор школы'
 * 
 * @author dmitry
 *
 */
@Service
@Transactional
class DirectorOfSchoolImpl implements DirectorOfSchool {

	@Lookup
	private SchoolAdapter getSchoolAdapter() {
		// Предполагается, что этот адаптер используется крайне редко
		return null;
	}
	
	

	@Override
	public void dismissTeacher(int schoolNumber, int id) throws UpdateException {
		Precondition.ifTrueThrow(schoolNumber < 1 || schoolNumber > 9999,
				"Недопустимый номер школы. Должен лежать в пределах 1-9999", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		

	}

	@Override
	public void recruitTeacher(int schoolNumber, Teacher teacher) throws IllegalParameterException, UpdateException {

	}

}
