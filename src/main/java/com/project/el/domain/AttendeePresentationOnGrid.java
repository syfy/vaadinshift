package com.project.el.domain;

public class AttendeePresentationOnGrid {
	long id;
	String firstName;
	String middleName;
	String lastName;
	String birthDate;
	String mobileNumber;
	String emailAddress;
	String faceBookAccountURL;
	String schoolName;
	long numberOfAttendedFellowship;

	public long getNumberOfAttendedFellowship() {
		return numberOfAttendedFellowship;
	}

	public void setNumberOfAttendedFellowship(long numberOfAttendedFellowship) {
		this.numberOfAttendedFellowship = numberOfAttendedFellowship;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaceBookAccountURL() {
		return faceBookAccountURL;
	}

	public void setFaceBookAccountURL(String faceBookAccountURL) {
		this.faceBookAccountURL = faceBookAccountURL;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
