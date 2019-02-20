package ru.abcd.example.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Сущность описывает ученика школы.
 * <p>
 * Документация - <a href="https://yandex.ru"> Описание тех. задания </a>
 * </p>
 * 
 * @author dmitry
 *
 */
@Entity
@Table(name = "students")
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Student extends Person {
	
	/**
	 * Телефон ученика. Может отсутствовать
	 */
	@Column(length = 12, nullable = true, unique = true)
	@Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
	String phone;
}
