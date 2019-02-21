package ru.abcd.example.controllers.departmentdirector;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ru.abcd.example.common.aop.AnnotationLogMethodArround;
import ru.abcd.example.controllers.ResponseError;
import ru.abcd.example.interactor.School;
import ru.abcd.example.interactor.Teacher;

/**
 * Контроллер доступа к функционалу директора департамента образования
 * 
 * @author dmitry
 *
 */
@CrossOrigin
@RestController
@Api(tags="Директора департамента образования")
@ResponseStatus(code = HttpStatus.OK)
@RequestMapping("department/director")
@ApiResponses({ @ApiResponse(code = 200, message = "Успешно"),
		@ApiResponse(code = 500, message = "Ошибка при вызове метода сервера", response = ResponseError.class) })
class DirectorOfDepartment {

	@Autowired
	private ru.abcd.example.interactor.departmentdirector.DirectorOfDepartment service;

	/**
	 * Добавление новой школы
	 * @param number Номер
	 * @return Школа
	 */
	@AnnotationLogMethodArround
	@ApiOperation("Добавление новой школы")
	@PostMapping("/schools")
	public School createSchool(@RequestParam(value = "number", required = true) int number) {
		return service.createSchool(number);
	}

	/**
	 * Метод возвращает список всех школ постранично<br>
	 * Объекты возвращаются в 'урезанном' представлении см.
	 * {@link com.fasterxml.jackson.annotation.JsonView}
	 * 
	 * @param page Номер страницы
	 * @param recordCount Кол-во записей на странице
	 * @return Коллекция школ
	 */
	@JsonView(ru.abcd.example.interactor.School.ShortView.class)
	@AnnotationLogMethodArround
	@ApiOperation("Чтение всех школ постранично")
	@GetMapping("/schools/all")
	public Collection<School> getAll(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "recordCount", required = true) int recordCount) {
		return service.getAll(page, recordCount);
	}

	/**
	 * Добавление нового учителя
	 * @param teacher Учитель
	 * @return
	 */
	@AnnotationLogMethodArround
	@ApiOperation("Добавление нового учителя")
	@PostMapping("/teachers")
	public Teacher createTeacher(@RequestBody Teacher teacher) {
		return service.createTeacher(teacher);
	}

}
