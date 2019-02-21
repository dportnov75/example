package ru.abcd.example.repository;

import org.springframework.stereotype.Service;

import ru.abcd.example.interactor.Student;
import ru.abcd.example.interactor.StudentAdapter;

/**
 * Реализация адаптера для моделей {@link ru.abcd.example.interactor.Student}
 * 
 * @author dmitry
 *
 */
@Service
class StudentAdapterImpl extends PersonAdapterImpl<Student, ru.abcd.example.repository.Student>
		implements StudentAdapter {

}
