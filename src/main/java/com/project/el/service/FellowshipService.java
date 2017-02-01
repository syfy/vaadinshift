package com.project.el.service;

import java.util.ArrayList;
import java.util.List;

import com.project.el.domain.Attendee;
import com.project.el.domain.AttendeePresentationOnGrid;
import com.project.el.domain.Fellowship;

public interface FellowshipService {
	List<Fellowship> findAll();
	void save(Fellowship fellowship);
	Fellowship get(Long id);
	Fellowship getOneJpql(Long id);
	ArrayList<AttendeePresentationOnGrid> getPresentationOfAttendeesList(Long id);
	void removeAtendeeFromFellowshopt(Fellowship fellowship, Attendee attendee);
}
