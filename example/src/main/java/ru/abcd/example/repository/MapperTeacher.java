package ru.abcd.example.repository;

/**
 * Интерфейс преобразования моделей учителей
 * 
 * </p>
 * Документация - <a href="http://mapstruct.org/"> Mapstruct</a>
 * <p>
 * 
 * @author dmitry
 *
 */
@org.mapstruct.Mapper(componentModel = "spring")
interface MapperTeacher
		extends MapperPerson<ru.abcd.example.interactor.Teacher, ru.abcd.example.repository.Teacher> {

}
