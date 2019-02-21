package ru.abcd.example.controllers.schooldirector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ru.abcd.example.common.aop.AnnotationLogMethodArround;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.controllers.ResponseError;

/**
 * Контроллер доступа к функционалу директора школы
 * 
 * @author dmitry
 *
 */
@CrossOrigin
@Api(tags = "Директора школ")
@RestController
@ResponseStatus(code = HttpStatus.OK)
@RequestMapping("school/director")
@ApiResponses({ @ApiResponse(code = 200, message = "Успешно"),
		@ApiResponse(code = 500, message = "Ошибка при вызове метода сервера", response = ResponseError.class) })
class DirectorOfSchool implements ru.abcd.example.interactor.schooldirector.DirectorOfSchool {

	@Autowired
	private ru.abcd.example.interactor.schooldirector.DirectorOfSchool service;

	@AnnotationLogMethodArround
	@ApiOperation("Увольнение учителя")
	@PutMapping("/teachers/dismiss")
	@Override
	public void dismissTeacher(@RequestParam(value = "school_number", required = true) int schoolNumber,
			@RequestParam(value = "teacher_id", required = true) int id) throws UpdateException {
		service.dismissTeacher(schoolNumber, id);
	}

	@AnnotationLogMethodArround
	@ApiOperation("Принять на работу")
	@PutMapping("/teachers/recruit")
	@Override
	public void recruitTeacher(@RequestParam(value = "school_number", required = true) int schoolNumber,
			@RequestParam(value = "teacher_id", required = true) int teacherId)
			throws IllegalParameterException, UpdateException {
		service.recruitTeacher(schoolNumber, teacherId);
	}

}
