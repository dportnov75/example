package ru.abcd.example.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ru.abcd.example.common.aop.AnnotationCatchException;
import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.Person;
import ru.abcd.example.interactor.PersonAdapter;

/**
 * Базовый адаптер для работы с персонами
 * 
 * @author dmitry
 *
 * @param <Dto> Модель домменого слоя
 * @param <Entity> Сущность
 */
abstract class PersonAdapterImpl<Dto extends Person, Entity extends ru.abcd.example.repository.Person>
		implements PersonAdapter<Dto> {

	@Autowired
	protected PersonsRepository<Entity> repository;

	@Autowired
	protected MapperPerson<Dto, Entity> mapper;

	@Override
	public Optional<Dto> findById(int id) {
		Optional<Entity> entity = repository.findById(id);
		return entity.isPresent() ? Optional.of(mapper.map(entity.get())) : Optional.empty();
	}

	@Override
	@AnnotationCatchException(value = CreateException.class, code = ExceptionCodes.REPOSITORY_SAVE_ERROR, message = "Ошибка при создании персоны. Cообщение - ")
	public Dto add(Dto person) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(person == null, "Задайте персону", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		// Какие-то еще проверки
		return mapper.map(repository.save(mapper.map(person)));
	}

	@Override
	public void update(Dto person) throws IllegalParameterException, UpdateException {
		Precondition.ifTrueThrow(person == null, "Задайте персону", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		Precondition.ifFalseThrow(repository.getOne(person.getId()) != null,
				"Ошибка редактирования. в системе нет персоны с идентификаторм -" + person.getId(),
				ExceptionCodes.REPOSITORY_SAVE_ERROR, UpdateException.class);
		// Какие-то еще проверки
		try {
			mapper.map(repository.save(mapper.map(person)));
		} catch (Exception e) {
			throw new CreateException("Ошибка при создании персоны. Cообщение - " + e.getMessage(),
					ExceptionCodes.REPOSITORY_SAVE_ERROR, e);
		}
	}

}
