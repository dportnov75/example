package ru.abcd.example.interactor.departmentdirector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import ru.abcd.example.interactor.School;
import ru.abcd.example.interactor.departmentdirector.DirectorOfDepartment;

/**
 * Пример сервиса обрабатывающего запросы GraphQL<br>
 * Вызвать <a href="http://localhost:9999/example/graphiql"> GraphiQl</a>
 * <p>
 * Выполнить - {@code {
  	findByNumber(number: 2) {
    	number
    	phones } 
    	}
    }
 * </p>
 * <p>
 * Файл с описанием и привязкой находится в {@code /resources/school.graphsqls}
 * 
 * @author dmitry
 *
 */
@Service
public class DirectorOfDepartmentGraphQlQueryHandler implements GraphQLQueryResolver {

	@Autowired
	private DirectorOfDepartment service;

	/**
	 * Пример обработчика запроса для GraphQL
	 * 
	 * @param number Номер
	 * @param name   Имя
	 * @return Школа
	 */
	public School findByNumber(int number) {
		return service.findByNumber(number).orElse(null);
	}
}
