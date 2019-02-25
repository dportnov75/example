package ru.abcd.example.interactor.schooldirector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.Decorator;
import ru.abcd.example.common.exceptions.IllegalParameterException;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.notification.TeacherDismissEvent;

/**
 * Декоратор {@link DirectorOfSchoolActor}
 * 
 * @author dmitry
 *
 */
@Decorator
@Service
public class DirectorOfSchoolDecorator implements DirectorOfSchool {

	@Autowired
	private DirectorOfSchool service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public void dismissTeacher(int schoolNumber, int id) throws UpdateException {
		service.dismissTeacher(schoolNumber, id);
		publisher.publishEvent(new TeacherDismissEvent(this, id));
	}

	@Override
	public void recruitTeacher(int schoolNumber, int teacherId) throws IllegalParameterException, UpdateException {
		service.recruitTeacher(schoolNumber, teacherId);
	}

}
