package com.project.el.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Fellowship  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	



	@ManyToMany(fetch = FetchType.EAGER)

	Set<Attendee> attendees = new HashSet<Attendee>();
	Date date;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public Set<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}

	




	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AttendeePresentationOnGridAggregate getAttendeeAsPresentation() {

		List<Attendee> convertedAttendeesToList = new ArrayList<Attendee>();

		convertedAttendeesToList.addAll(attendees);
		AttendeePresentationOnGridAggregate returnValue = new AttendeePresentationOnGridAggregate(convertedAttendeesToList);

		return returnValue;

	}
	
	public void addAttendee(Attendee attendee){
		if(!attendees.contains(attendee)){
			attendees.add(attendee);
		}


		
	}
	
	public void removeAttendee(Attendee attendee){
		if(attendees.contains(attendee)){
			attendees.remove(attendee);

		}
	}
	
	public boolean containsDuplicateById(Long id){


		for(Attendee attendee: attendees){

			if(attendee.getId() == id){
				return true;
			}
		}
		return false;
	}

}
