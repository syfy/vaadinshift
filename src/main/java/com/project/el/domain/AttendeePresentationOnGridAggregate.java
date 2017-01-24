package com.project.el.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.el.service.AttendeeService;
import com.project.el.service.FellowshipService;

public class AttendeePresentationOnGridAggregate {
	List<AttendeePresentationOnGrid> attendeePresentationOnGridAggregate;

	public AttendeePresentationOnGridAggregate(List<Attendee> attendees){
		
		attendeePresentationOnGridAggregate= new ArrayList<AttendeePresentationOnGrid>();
			for(Attendee attendee:attendees){
			
				
				AttendeePresentationOnGrid attendeePresentationOnGridrow = new AttendeePresentationOnGrid();
				attendeePresentationOnGridrow.setBirthDate(attendee.getPersonalInformation().getBirthDate().toString());
				attendeePresentationOnGridrow.setEmailAddress(attendee.getPersonalInformation().getEmailAddress());
				attendeePresentationOnGridrow.setFaceBookAccountURL(attendee.getPersonalInformation().getFaceBookAccountURL());
				attendeePresentationOnGridrow.setId(attendee.getId());
				attendeePresentationOnGridrow.setLastName(attendee.getPersonalInformation().getLastName());
				attendeePresentationOnGridrow.setFirstName(attendee.getPersonalInformation().getFirstName());
				attendeePresentationOnGridrow.setMobileNumber(attendee.getPersonalInformation().getMobileNumber());
				attendeePresentationOnGridrow.setSchoolName(attendee.getPersonalInformation().getSchoolName());
				attendeePresentationOnGridrow.setNumberOfAttendedFellowship(attendee.getAttendedFellowships().size());
				attendeePresentationOnGridAggregate.add(attendeePresentationOnGridrow);
				
			}

	}
	

	public AttendeePresentationOnGridAggregate() {
		attendeePresentationOnGridAggregate= new ArrayList<AttendeePresentationOnGrid>();
	}


	public List<AttendeePresentationOnGrid> getAttendeePresentationOnGridAggregate() {
		return attendeePresentationOnGridAggregate;
	}

	public void setAttendeePresentationOnGridAggregate(
			List<AttendeePresentationOnGrid> attendeePresentationOnGridAggregate) {
		this.attendeePresentationOnGridAggregate = attendeePresentationOnGridAggregate;
	}
	
	
	
}
