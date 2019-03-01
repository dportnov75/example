package ru.abcd.example.repository;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Абстрактный класс, описывающий персону
 * 
 * @author dmitry
 *
 */
@MappedSuperclass
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	int id;

	/**
	 * Имя
	 */
	@Column(length = 32, nullable = false)
	@Size(min = 1, max = 32)
	String firstName;

	/**
	 * Фамилия
	 */
	@Column(length = 32, nullable = false)
	@Size(min = 1, max = 32)
	String secondName;

	/**
	 * Школа, к которой 'привязана' персона.<br>
	 * Может быть не привязаной ни к одной школе
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	School school;
	
	/**
	 * Версия, для оптимистичной блокировки
	 */
	@Version
	int version;
}
