package ru.abcd.example.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.abcd.example.repository.Teacher.Qualification;

/**
 * Пример теста репозитория учителей
 * 
 * @author dmitry
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TeachersRepositoryTest {

	@Autowired
	private TeachersRepository repository;

	@Before
	public void befor() {

	}

	@After
	public void after() {

	}

	@Test
	public void findByFirstNameAndSecondName() {
		Teacher t1 = repository
				.save(Teacher.builder().firstName("1").secondName("2").qualification(Qualification.HIGH).build());
		Teacher t2 = repository
				.save(Teacher.builder().firstName("1").secondName("2").qualification(Qualification.HIGH).build());
		Teacher t3 = repository
				.save(Teacher.builder().firstName("3").secondName("4").qualification(Qualification.LOW).build());
		assertThat(repository.findByFirstNameAndSecondName("1", "2").size(), is(2));
		assertThat(repository.findByFirstNameAndSecondName("1", "2"), hasItems(t1, t2));

		assertThat(repository.findByFirstNameAndSecondName("2", "2").size(), is(0));
		assertThat(repository.findByFirstNameAndSecondName("3", "4").size(), is(1));
		assertThat(repository.findByFirstNameAndSecondName("3", "4"), hasItem(t3));

		assertThat(repository.findByFirstNameAndSecondName("8", "9"), hasSize(0));
	}
}
