package ru.abcd.example.repository;

import ru.abcd.example.interactor.School;

/**
 * Интерфейс преобразования моделей школ
 * 
 * </p>
 * Документация - <a href="http://mapstruct.org/"> Mapstruct</a>
 * <p>
 * 
 * @author dmitry
 *
 */
@org.mapstruct.Mapper(componentModel = "spring")
interface MapperSchool {

	/**
	 * Преобразовывает сущность в модель доменного слоя
	 * 
	 * @param entity Cущность
	 * @return Модель доменного слоя
	 */
	public School map(ru.abcd.example.repository.School entity);

	/**
	 * Преобразует модель доменного слоя в сущность
	 * 
	 * @param dto Модель доменного слоя
	 * @return Сущность
	 */
	public ru.abcd.example.repository.School map(School dto);

}
