package ru.abcd.example.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Репозиторий доступа к объектам наследникам от
 * {@link ru.abcd.example.repository.Person}
 * 
 * @author dmitry
 *
 */
@NoRepositoryBean
interface PersonsRepository<T extends Person> extends JpaRepository<T, Integer> {

	/**
	 * Метод ищет персон по имени и фамилии
	 * 
	 * @param firstName  Имя
	 * @param secondName Фамилия
	 * @return Набор персон, удовлетворяющих условиям поиска
	 */
	public Set<T> findByFirstNameAndSecondName(String firstName, String secondName);
}
