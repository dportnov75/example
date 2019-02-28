package ru.abcd.example.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.aop.AnnotationCatchUnhandledException;
import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.Precondition;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.interactor.School;
import ru.abcd.example.interactor.SchoolAdapter;
import ru.abcd.example.interactor.Teacher;

/**
 * Реализация адаптера для моделей {@link ru.abcd.example.interactor.School}
 * 
 * @author dmitry
 *
 */
@Service
//Предположим, что методы этого адаптера вызываются крайне редко. Делаем его prototype 
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class SchoolAdapterImpl implements SchoolAdapter {

	@Autowired
	private SchoolsRepository repository;

	@Autowired
	private TeachersRepository repositoryTeacher;

	@Autowired
	private MapperSchool mapper;

	@Override
	public Optional<School> findByNumber(int number) {
		Optional<ru.abcd.example.repository.School> school = repository.findById(number);
		return school.isPresent() ? Optional.of(mapper.map(school.get())) : Optional.empty();
	}

	@AnnotationCatchUnhandledException(value = CreateException.class, code = ExceptionCodes.REPOSITORY_SAVE_ERROR, message = "Ошибка при создании школы. Cообщение - ")
	@Override
	public School add(School school) throws IllegalParameterException, CreateException {
		Precondition.ifTrueThrow(school == null, "Не задана школа", ExceptionCodes.INCORRECT_PARAMETER,
				IllegalParameterException.class);
		Precondition.ifTrueThrow(repository.findById(school.getNumber()).isPresent(),
				"Уже есть школа с номером " + school.getNumber(), ExceptionCodes.REPOSITORY_SAVE_ERROR,
				CreateException.class);
		return mapper.map(repository.save(mapper.map(school)));
	}

	@Override
	public Collection<Teacher> getSchollTeachers(int number) {
		// TODO Убрать заглушку
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException {
		return repository.findAll(PageRequest.of(page, recordCount)).stream().map(m -> mapper.map(m))
				.collect(Collectors.toList());
	}

	@AnnotationCatchUnhandledException(value = UpdateException.class, code = ExceptionCodes.REPOSITORY_SAVE_ERROR, message = "Ошибка при изменении. Cообщение - ")
	@Override
	public void removeTeacher(int schoolNumber, int teacherId) throws UpdateException {
		Optional<ru.abcd.example.repository.School> entity = repository.findById(schoolNumber);
		if (entity.isPresent()) {
			entity.get().getTeachers().removeIf(filter -> filter.getId() == teacherId);
			repository.save(entity.get());
		}
	}

	@Override
	@AnnotationCatchUnhandledException(value = CreateException.class, code = ExceptionCodes.REPOSITORY_SAVE_ERROR, message = "Ошибка при добавлении учителя в школу. Cообщение - ")
	public void addTeacher(int schoolNumber, int teacherId) {
		Optional<ru.abcd.example.repository.School> entitySchool = repository.findById(schoolNumber);
		Optional<ru.abcd.example.repository.Teacher> entityTeacher = repositoryTeacher.findById(teacherId);
		if (entitySchool.isPresent() && entityTeacher.isPresent()) {
			entitySchool.get().getTeachers().add(entityTeacher.get());
			repository.save(entitySchool.get());
		}
	}

}
