package ru.abcd.example.repository;

import org.springframework.stereotype.Service;

import ru.abcd.example.interactor.Teacher;
import ru.abcd.example.interactor.TeacherAdapter;

/**
 * Реализация адаптера для моделей {@link ru.abcd.example.interactor.Teacher}
 * 
 * @author dmitry
 *
 */
@Service
class TeacherAdapterImpl extends PersonAdapterImpl<Teacher, ru.abcd.example.repository.Teacher>
		implements TeacherAdapter {

}
