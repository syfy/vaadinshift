package com.project.el.builders;

import java.sql.Date;

import com.project.el.domain.Attendee;
import com.project.el.domain.PersonalInformation;

public class AttendeeBuilder {
	Attendee attendee;
	PersonalInformation personalInformation;

	public AttendeeBuilder() {
		attendee = new Attendee();
		personalInformation = new PersonalInformation();
		attendee.setPersonalInformation(personalInformation);
	}

	public	AttendeeBuilder setFirstName(String firstName) {
		personalInformation.setFirstName(firstName);
		return this;
	}

	public	AttendeeBuilder setMiddleName(String middleName) {

		return this;

	}

	public	AttendeeBuilder setLastName(String lastName) {
		personalInformation.setLastName(lastName);
		return this;

	}

	public	AttendeeBuilder setBirthDate(Date birthDate) {
		personalInformation.setBirthDate(birthDate);
		return this;

	}

	public	AttendeeBuilder setMobileNumber(String mobileNumber) {

		personalInformation.setMobileNumber(mobileNumber);
		return this;

	}

	public	AttendeeBuilder setEmailAddress(String emailAddress) {
		personalInformation.setEmailAddress(emailAddress);
		return this;

	}

	public	AttendeeBuilder setFaceBookAccountURL(String facebookUrl) {
		personalInformation.setFaceBookAccountURL(facebookUrl);
		return this;

	}

	public	AttendeeBuilder setSchoolName(String schoolName) {
		personalInformation.setSchoolName(schoolName);
		return this;

	}

	public Attendee build() {
		return attendee;
	}

}
