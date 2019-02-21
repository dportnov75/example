package ru.abcd.example.repository;

/**
 * Базовый интерфейс преобразования персон
 * 
 * </p>
 * Документация - <a href="http://mapstruct.org/"> Mapstruct</a>
 * <p>
 * @author dmitry
 *
 * @param <D> Модель доменного слоя
 * @param <E> Сущность репозитария
 */
interface MapperPerson<D extends ru.abcd.example.interactor.Person, E extends Person> {

	/**
	 * Преобразует сущность в модель доменного слоя
	 * 
	 * @param entity Сущность
	 * @return модель доменного слоя
	 */
	public D map(E entity);

	/**
	 * Преобразует модель доменного слоя в сущность
	 * 
	 * @param dto Модель доменного слоя
	 * @return Сущность
	 */
	public E map(D dto);
}
