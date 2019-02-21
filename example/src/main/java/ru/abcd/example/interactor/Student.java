package ru.abcd.example.interactor;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Модель ученика
 * @author dmitry
 *
 */
@SuperBuilder
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Student extends Person {

}
