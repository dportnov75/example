package ru.abcd.example.repository;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Сущность описывает школу
 * 
 * @author dmitry
 *
 */
@Entity
@Table(name = "schools")
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
class School {

	/**
	 * Номер школы. Предположим, что это уникальное число
	 */
	@Min(1)
	@Max(9999)
	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	int number;

	/**
	 * Коллекция телефонов школы
	 */
	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "school_phones", joinColumns = @JoinColumn(name = "school_id"))
	@Column(name = "phone", length = 12, nullable = false, unique = true)
	Set<@Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$") String> phones = new HashSet<>();

	/**
	 * Список преподавателей школы
	 */
	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER)
	Set<Teacher> teachers = new HashSet<>();

	/**
	 * Список учащихся
	 */
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	Set<Student> students = new HashSet<>();

	/**
	 * Версия, для оптимистичной блокировки
	 */
	@Version
	int version;

}
