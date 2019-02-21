package ru.abcd.example.interactor.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Модель абстрактной персоны
 * @author dmitry
 *
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
public abstract class Person {
	
	/**
	 * Идентификатор
	 */
	@EqualsAndHashCode.Include
	Integer id;
	
	/**
	 * Имя 
	 */
	String firstName;
	
	/**
	 * Фамилия
	 */
	String secondName;
	
}
