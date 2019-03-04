package ru.abcd.example.interactor.departmentdirector;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.interactor.PersonAdapter;
import ru.abcd.example.interactor.School;
import ru.abcd.example.interactor.SchoolAdapter;
import ru.abcd.example.interactor.Student;
import ru.abcd.example.interactor.Teacher;

/**
 * Реализация функционала акторов типа 'директора департамента образования'
 * 
 * @author dmitry
 *
 */
@Service
@Transactional
class DirectorOfDepartmentImpl implements DirectorOfDepartment {

	/**
	 * Возвращает каждый раз новый экземпляр
	 * @return
	 */
	@Lookup
	protected SchoolAdapter getSchoolAdapter() {
		return null;
	}

	@Autowired
	private PersonAdapter<Teacher> adapterTeacher;

	@Autowired
	private PersonAdapter<Student> adapterStudent;

	@Override
	public School createSchool(int number) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(number < 1 || number > 9999, "Номер школы должен лежать в диапазоне 1-9999",
				ExceptionCodes.INCORRECT_PARAMETER, IllegalParameterException.class);
		return getSchoolAdapter().add(School.builder().number(number).build());
	}

	@Override
	public Teacher createTeacher(Teacher teacher) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(teacher == null, "Не задан учитель", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		return adapterTeacher.add(teacher);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException {
		Precondition.ifTrueThrow(page < 0 || recordCount < 0,
				"Номер страницы и кол-во записей должно быть не отрицательным числом",
				ExceptionCodes.INCORRECT_PARAMETER, IllegalParameterException.class);
		// Какие-то другие проверки....
		return getSchoolAdapter().getAll(page, recordCount);
	}

	@Override
	public Student createStudent(Student student) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(student == null, "Не задан ученик", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		// Какие-то другие проверки....
		return adapterStudent.add(student);
	}

	@Override
	public Optional<School> findByNumber(int id) {
		return getSchoolAdapter().findByNumber(id);
	}

}
