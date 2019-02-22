package ru.abcd.example.notification;

import org.springframework.context.ApplicationEvent;

/**
 * Событие об уволенении учителя (внутри процесса)
 * 
 * @author dmitry
 *
 */
public class TeacherDismissEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210517634146388591L;

	/**
	 * Идентификатор
	 */
	private final int id;

	
	
	@Override
	public String toString() {
		return "TeacherDissmisEvent [id=" + id + "]";
	}

	/**
	 * Идентификатор учителя
	 * 
	 * @return Идентификатор
	 */
	public int getId() {
		return id;
	}

	public TeacherDismissEvent(Object source, int id) {
		super(source);
		this.id = id;
	}
}
