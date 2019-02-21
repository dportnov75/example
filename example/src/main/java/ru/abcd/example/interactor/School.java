package ru.abcd.example.interactor.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Модель школы
 * 
 * @author dmitry
 *
 */
@Data
@SuperBuilder
@ToString(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class School {

	/**
	 * Интерфейс для Jackson. Здесь будет содержаться короткое представление объекта
	 * 
	 * @author dmitry
	 *
	 */
	public interface ShortView {

	}

	
	/**
	 * Номер
	 */
	@ToString.Include
	@JsonView(ShortView.class)
	int number;

	/**
	 * Список телефонов школы
	 */
	@Builder.Default
	Set<String> phones = new HashSet<>();

	/**
	 * Список учителей школы
	 */
	@Builder.Default
	Collection<Teacher> teachers = Collections.emptyList();
}
