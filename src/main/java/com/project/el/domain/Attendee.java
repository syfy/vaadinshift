package com.project.el.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Attendee extends AbstractPersistable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	PersonalInformation personalInformation;
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = " fellowship_attendees", joinColumns = @JoinColumn(name = "attendees_id") , inverseJoinColumns = @JoinColumn(name = "fellowship_id") )

//	@JoinTable(name = " fellowship_attendees")
  //  Set<Fellowship> attendedFellowships;
    List<Fellowship> attendedFellowships = new ArrayList<>();





	public List<Fellowship> getAttendedFellowships() {
		return attendedFellowships;
	}


	public void setAttendedFellowships(List<Fellowship> attendedFellowships) {
		this.attendedFellowships = attendedFellowships;
	}


	public void addAttendedFellowship(Fellowship fellowship){
	
		attendedFellowships.add(fellowship);
	}



	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(PersonalInformation personalInformation) {
		this.personalInformation = personalInformation;
	}
	
	

}
