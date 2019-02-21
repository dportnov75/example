package ru.abcd.example.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.Teacher;
import ru.abcd.example.interactor.TeacherAdapter;

/**
 * Реализация адаптера для моделей
 * {@link ru.abcd.example.interactor.Teacher}
 * 
 * @author dmitry
 *
 */
@Service
class TeacherAdapterImpl implements TeacherAdapter {

	@Autowired
	private TeachersRepository repository;

	@Autowired
	private MapperPerson<Teacher, ru.abcd.example.repository.Teacher> mapper;

	@Override
	public Optional<Teacher> findById(int id) {
		Optional<ru.abcd.example.repository.Teacher> entity = repository.findById(id);
		return entity.isPresent() ? Optional.of(mapper.map(entity.get())) : Optional.empty();
	}

	@Override
	public Teacher add(Teacher person) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(person == null, "Не задан учитель", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		// проверки полей.....
		try {
			return mapper.map(repository.save(mapper.map(person)));
		} catch (Exception e) {
			throw new CreateException("Ошибка при добавлении нового учителя. " + e.getLocalizedMessage(),
					ExceptionCodes.REPOSITORY_SAVE_ERROR, e);
		}
	}

	@Override
	public void update(Teacher person) throws IllegalParameterException, UpdateException {
		Precondition.ifTrueThrow(person == null, "Не задан учитель", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		Precondition.ifFalseThrow(repository.getOne(person.getId()) == null,
				"В системе нет учителя с id = " + person.getId(), ExceptionCodes.REPOSITORY_SAVE_ERROR,
				UpdateException.class);
		try {
			repository.save(mapper.map(person));
		} catch (Exception e) {
			throw new UpdateException("Ошибка при сохранении учителя. " + e.getMessage(),
					ExceptionCodes.REPOSITORY_SAVE_ERROR, e);
		}
	}

}
