package ru.abcd.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер доступа к функционалу директора школы
 * @author dmitry
 *
 */
@CrossOrigin 
@RestController
@ResponseStatus(code = HttpStatus.OK)
@RequestMapping("school/director")
@ApiResponses({ @ApiResponse(code = 200, message = "Успешно"),
	@ApiResponse(code = 500, message = "Ошибка при вызове метода сервера", response = ResponseError.class) })
class DirectorOfSchool {

}
