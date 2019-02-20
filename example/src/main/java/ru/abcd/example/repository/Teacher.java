package ru.abcd.example.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Сущность описывает учителя
 * 
 * @author dmitry
 *
 */
@Entity
@Table(name = "teachers", indexes = { @Index(name = "qualification_index", columnList = "qualification") })
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
class Teacher extends Person {

	/**
	 * Типы квалификации учителей
	 * 
	 * @author dmitry
	 *
	 */
	public enum Qualification {
		HIGH("Высокая"), MEDIUM("Средняя"), LOW("Низкая");

		private String qualification;

		@Override
		public String toString() {
			return qualification;
		}

		Qualification(String qualification) {
			this.qualification = qualification;
		}
	}

	/**
	 * Квалификация
	 */
	@Enumerated
	@Column(columnDefinition = "smallint", nullable = false) // Экономим место
	@Builder.Default
	Qualification qualification = Qualification.MEDIUM;
}
