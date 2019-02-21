package ru.abcd.example.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.IllegalParameterException;
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
	private MapperSchool mapper;

	@Override
	public Optional<School> findById(int id) {
		return null;
	}

	@Override
	public School add(int number) throws IllegalParameterException, CreateException {
		return null;
	}

	@Override
	public Collection<Teacher> getSchollTeachers(int number) {
		return null;
	}

	@Override
	public Collection<School> getAll(int page, int recordCount) throws IllegalParameterException {
		return repository.findAll(PageRequest.of(page, recordCount)).stream().map(m -> mapper.map(m))
				.collect(Collectors.toList());
	}

}
