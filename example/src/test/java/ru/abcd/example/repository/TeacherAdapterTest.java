package ru.abcd.example.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ru.abcd.example.common.exceptions.CreateException;
import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.interactor.Teacher;

/**
 * Пример теста адаптера для учителей<br>
 * Следует добавить в pom <br>
 * 
 * {@code <properties> <m2e.apt.activation>jdt_apt</m2e.apt.activation> </properties>}
 * <br>
 * Иначе не будет создаваться экземпляр MapperTeacher
 * 
 * @author dmitry
 *
 */
@RunWith(SpringRunner.class)
public class TeacherAdapterTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Spy
	private MapperTeacher mapper = Mappers.getMapper(MapperTeacher.class);

	@InjectMocks
	private TeacherAdapterImpl adapter;

	@MockBean
	private TeachersRepository repository;

	@Before
	public void befor() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Добавление учителя
	 */
	@Test
	public void test_add() {
		ru.abcd.example.repository.Teacher t1 = ru.abcd.example.repository.Teacher.builder().firstName("1")
				.secondName("2").build();
		Mockito.when(repository.save(t1))
				.thenReturn(ru.abcd.example.repository.Teacher.builder().firstName("1").secondName("2").id(2).build());
		Teacher t2 = adapter.add(Teacher.builder().firstName("1").secondName("1").build());
		assertThat(t2, is(not(nullValue())));
		assertThat(t2.getId(), greaterThan(0));
		assertThat(t2.getFirstName(), equalTo("1"));
		assertThat(t2.getSecondName(), equalTo("2"));
	}

	/**
	 * Неожиданное исключение из репозитория
	 */
	@Test
	public void test_repository_exception_handle() {
		exception.expect(CreateException.class);
		exception.expect(hasProperty("exceptionCode", is(ExceptionCodes.REPOSITORY_SAVE_ERROR)));
		// Генерим неожиданное исключение
		Mockito.when(
				repository.save(ru.abcd.example.repository.Teacher.builder().firstName("1").secondName("1").build()))
				.thenThrow(new RuntimeException("qwer"));
		adapter.add(Teacher.builder().firstName("1").secondName("1").build());
	}

	/**
	 * Передача null
	 */
	@Test
	public void test_null_parameter_exception() {
		exception.expect(IllegalParameterException.class);
		exception.expect(hasProperty("exceptionCode", is(ExceptionCodes.INCORRECT_PARAMETER)));
		adapter.add(null);
	}
}
