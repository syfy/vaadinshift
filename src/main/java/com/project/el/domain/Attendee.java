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
public class Attendee{
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
    Set<Fellowship> attendedFellowships;

	public Set<Fellowship> getAttendedFellowships() {
		return attendedFellowships;
	}


	public void setAttendedFellowships(Set<Fellowship> attendedFellowships) {
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
	
	boolean equals(Long id){
		
		if(this.getId()==id){
			return true;

		}
		return false;
		
	}
	


}
