package com.project.el.ui.models;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.el.domain.Attendee;
import com.project.el.service.AttendeeService;
import com.vaadin.spring.annotation.ViewScope;
@Component
@ViewScope
public class AttendeeModel extends Observable  {
	@Autowired
	AttendeeService attendeeService;
	
	
	Attendee attendee;
    public void initialize(Long id) {
        this.attendee =attendeeService.get(id);
    }
    
    Attendee getAttendee(){
    	return attendee;
    }
    

}
