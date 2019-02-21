package ru.abcd.example.repository;

/**
 * Интерфейс преобразования моделей учеников
 * 
 * </p>
 * Документация - <a href="http://mapstruct.org/"> Mapstruct</a>
 * <p>
 * 
 * @author dmitry
 *
 */
@org.mapstruct.Mapper(componentModel = "spring")
interface MapperStudent extends MapperPerson<ru.abcd.example.interactor.Student, ru.abcd.example.repository.Student> {

}
