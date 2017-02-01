package com.project.el.service;

import java.util.Collection;
import java.util.List;

import com.project.el.domain.Attendee;

public interface AttendeeService {
	List<Attendee> findAll();
	void save(Attendee attendee);
	void save(Collection<?> attendees);
	Attendee get(Long id);
	Attendee getOneJpql(Long id);
	List<Attendee> findAllJpql();

}
