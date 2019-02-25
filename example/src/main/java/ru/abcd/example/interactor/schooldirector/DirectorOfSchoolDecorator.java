package ru.abcd.example.interactor.schooldirector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ru.abcd.example.common.Decorator;
import ru.abcd.example.common.exceptions.UpdateException;
import ru.abcd.example.notification.TeacherDismissEvent;

/**
 * Декоратор {@link DirectorOfSchoolActor}. <br>
 * Для уменьшения связанности и соблюдения принципа L из SOLID, по-хорошему,
 * надо бы унаследоваться от {@link DirectorOfSchool}
 * 
 * @author dmitry
 *
 */
@Decorator
@Service
public class DirectorOfSchoolDecorator extends DirectorOfSchoolActor {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public void dismissTeacher(int schoolNumber, int id) throws UpdateException {
		super.dismissTeacher(schoolNumber, id);
		publisher.publishEvent(new TeacherDismissEvent(this, id));
	}

}
