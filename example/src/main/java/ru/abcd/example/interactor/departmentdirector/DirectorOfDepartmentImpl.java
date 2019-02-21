package ru.abcd.example.interactor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.abcd.example.exceptions.CreateException;
import ru.abcd.example.exceptions.ExceptionCodes;
import ru.abcd.example.exceptions.IllegalParameterException;
import ru.abcd.example.exceptions.Precondition;
import ru.abcd.example.interactor.dto.School;
import ru.abcd.example.interactor.dto.Teacher;

/**
 * Реализация функционала акторов типа 'директора департамента образования'
 * 
 * @author dmitry
 *
 */
@Service
@Transactional
public class DirectorOfDepartmentImpl implements DirectorOfDepartment {

	@Autowired
	private SchoolAdapter adapter;
	
	@Override
	public School createSchool(int number) throws IllegalParameterException, CreateException {
		return null;
	}

	@Override
	public Teacher createTeacher(Teacher teacher) throws IllegalParameterException, CreateException {
		return null;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException {
		Precondition.ifTrueThrow(page < 0 || recordCount < 0,
				"Номер страницы и кол-во записей должно быть не отрицательным числом", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		return adapter.getAll(page, recordCount);
	}

}
