package ru.abcd.example.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Пример теста репозитория школ
 * @author dmitry
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SchoolsRepositoryTest {

	@Autowired
	private SchoolsRepository repository;
	
	
	/**
	 * сохранение данных
	 */
	@Test
	public void test_save() {
		String phone1 = "81112223344";
		String phone2 = "91112223344";
		School s1 = repository.save(School.builder().phone(phone1).phone(phone2).phone(phone2).number(1).build());
		assertThat(s1.getPhones(), is(not(nullValue())));
		assertThat(s1.getPhones(), hasSize(2));
		assertThat(s1.getPhones(), containsInAnyOrder(phone2, phone1));
		assertThat(s1.getStudents(), is(not(nullValue())));
		assertThat(s1.getTeachers(), is(not(nullValue())));
		assertThat(s1.getTeachers(), hasSize(0)); 
		assertThat(s1.getNumber(), is(1));
	}
}
