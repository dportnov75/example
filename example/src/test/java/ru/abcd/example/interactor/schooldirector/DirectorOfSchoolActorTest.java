package ru.abcd.example.interactor.schooldirector;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.interactor.SchoolAdapter;

/**
 * Тест функционала дирректора школы
 * 
 * @author dmitry
 *
 */
@RunWith(SpringRunner.class)
public class DirectorOfSchoolActorTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock
	private SchoolAdapter adapter;

	@Spy
	private DirectorOfSchoolActor service;

	@Before
	public void befor() {
		MockitoAnnotations.initMocks(this);
		// Если не использовать аннотации, то надо указать таким боразом -
		/* adapter = Mockito.mock(SchoolAdapter.class);*/
		/* service = Mockito.spy(DirectorOfSchoolActor.class);*/
		// В данном случае, т.к. над методом используется @Lookup, то следует 'руками'
		// указать, что возвращает метод
		Mockito.when(service.getSchoolAdapter()).thenReturn(adapter);
	}

	@Test
	public void test_dismiss_exception() {
		exception.expect(IllegalParameterException.class);
		exception.expect(hasProperty("exceptionCode", is(ExceptionCodes.INCORRECT_PARAMETER)));
		service.dismissTeacher(-1, 2);
	}

	@Test
	public void test_dismiss() {
		service.dismissTeacher(1, 2);
		Mockito.verify(adapter).removeTeacher(1, 2);
	}

}
