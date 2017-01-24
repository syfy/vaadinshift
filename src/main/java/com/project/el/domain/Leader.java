package com.project.el.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Leader {
	@OneToOne
	Attendee attendee;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany
	Set<Attendee> followers;
	public Attendee getAttendee() {
		return attendee;
	}
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}

	public Set<Attendee> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<Attendee> followers) {
		this.followers = followers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
