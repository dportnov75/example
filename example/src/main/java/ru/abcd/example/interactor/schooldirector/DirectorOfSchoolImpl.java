package ru.abcd.example.interactor.schooldirector;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.SchoolAdapter;

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
	public SchoolAdapter getSchoolAdapter() {
		// Предполагается, что этот адаптер используется крайне редко
		return null;
	}

	@Override
	public void dismissTeacher(int schoolNumber, int id) throws IllegalParameterException, UpdateException {
		Precondition.ifTrueThrow(schoolNumber < 1 || schoolNumber > 9999,
				"Недопустимый номер школы. Должен лежать в пределах 1-9999", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		try {
			getSchoolAdapter().removeTeacher(schoolNumber, id);
		} catch (UpdateException e) {
			throw e;
		} catch (Exception e) {
			throw new UpdateException("Ошибка при увольнении учителя. Сообщение - " + e.getMessage(),
					ExceptionCodes.REPOSITORY_SAVE_ERROR, e);
		}
	}

	@Override
	public void recruitTeacher(int schoolNumber, int teacherId) throws IllegalParameterException, UpdateException {
		Precondition.ifTrueThrow(schoolNumber < 1 || schoolNumber > 9999,
				"Недопустимый номер школы. Должен лежать в пределах 1-9999", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		getSchoolAdapter().addTeacher(schoolNumber, teacherId);
	}

}
