package ru.abcd.example.interactor.schooldirector;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ru.abcd.example.common.exceptions.ExceptionCodes;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.interactor.SchoolAdapter;

/**
 * Тест функционала дирректора школы
 * @author dmitry
 *
 */
@RunWith(SpringRunner.class)
public class DirectorOfSchoolActorTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@MockBean
	private SchoolAdapter adapter;
	
	@InjectMocks
	private DirectorOfSchoolActor service;

	@Before
	public void befor() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before()
	public void init() {
		
	}
	
	@Test
	public void test_dismiss_exception() {
		exception.expect(IllegalParameterException.class);
		exception.expect(hasProperty("exceptionCode", is(ExceptionCodes.INCORRECT_PARAMETER)));
		service.dismissTeacher(-1, 11);
	}
	
}
