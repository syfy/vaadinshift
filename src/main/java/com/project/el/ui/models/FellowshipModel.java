package com.project.el.ui.models;

import java.util.ArrayList;
import java.util.Observable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.el.domain.Attendee;
import com.project.el.domain.AttendeePresentationOnGrid;
import com.project.el.domain.AttendeePresentationOnGridAggregate;
import com.project.el.domain.Fellowship;
import com.project.el.domain.PersonalInformation;
import com.project.el.service.AttendeeService;
import com.project.el.service.FellowshipService;
import com.project.el.service.PersonalInformationService;
import com.vaadin.spring.annotation.ViewScope;
@Component
@ViewScope
@Transactional
public class FellowshipModel extends Observable {
	Fellowship fellowship;
	@Autowired
	FellowshipService fellowshipService;
	
	@Autowired
	PersonalInformationService personalInformationService;
	
	@Autowired
	AttendeeModel attendeeModel;
	@Autowired
	AttendeeService attendeeService;
	
	public Fellowship getFellowship() {
		return fellowship;
	}
    public void initialize(Long id) {
        this.fellowship =fellowshipService.getOneJpql(id);
    }

	public AttendeePresentationOnGridAggregate getPresentationOfAttendees(){
		return fellowship.getAttendeeAsPresentation();
		
	}
	
	public ArrayList<AttendeePresentationOnGrid> getPresentationOfAttendeesList(){
		
		ArrayList<AttendeePresentationOnGrid> attendeePresentationOnGridAggregate= new ArrayList<AttendeePresentationOnGrid>();
			for(Attendee attendee:this.fellowship.getAttendees()){
				
			//	System.out.println(attendeeService.get(attendee.getId()).getPersonalInformation().getEmailAddress());
				//PersonalInformation personalInformation = attendee.getPersonalInformation();
				//AttendeePresentationOnGrid attendeePresentationOnGridrow = new AttendeePresentationOnGrid();
				//attendeePresentationOnGridrow.setBirthDate(personalInformation.getBirthDate().toString());
			//	attendeePresentationOnGridrow.setEmailAddress(attendee.getPersonalInformation().getEmailAddress());
			//	attendeePresentationOnGridrow.setFaceBookAccountURL(attendee.getPersonalInformation().getFaceBookAccountURL());
			//	attendeePresentationOnGridrow.setId(attendee.getId());
			//	attendeePresentationOnGridrow.setLastName(attendee.getPersonalInformation().getLastName());
			//	attendeePresentationOnGridrow.setFirstName(attendee.getPersonalInformation().getFirstName());
			///	attendeePresentationOnGridrow.setMobileNumber(attendee.getPersonalInformation().getMobileNumber());
			//	attendeePresentationOnGridrow.setSchoolName(attendee.getPersonalInformation().getSchoolName());
			//	attendeePresentationOnGridAggregate.add(attendeePresentationOnGridrow);
				
			}
			return attendeePresentationOnGridAggregate;

	}
	
	public boolean containsDuplicateById(Long id){
	

		for(Attendee attendee: this.fellowship.getAttendees()){
	
			if(attendee.getId() == id){
				return true;
			}
		}
		return false;
	}
}
