package com.project.el.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.el.dao.FellowshipDAO;
import com.project.el.domain.Attendee;
import com.project.el.domain.AttendeePresentationOnGrid;
import com.project.el.domain.Fellowship;
import com.project.el.domain.PersonalInformation;

@Service
@Transactional
public class FellowshipServiceImpl implements FellowshipService {
	@Autowired
	FellowshipDAO fellowshipDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Fellowship> findAll() {

		return fellowshipDao.findAll();
	}

	@Override
	public void save(Fellowship fellowship) {

		fellowshipDao.save(fellowship);
	}

	@Override
	public void removeAtendeeFromFellowshopt(Fellowship fellowship, Attendee attendee) {

		fellowship.removeAttendee(attendee);
		fellowshipDao.save(fellowship);
	}

	@Override
	public Fellowship getOneJpql(Long id) {
		Query query = entityManager.createQuery(
				"SELECT  fellowship FROM Fellowship fellowship LEFT JOIN FETCH fellowship.attendees attendees LEFT JOIN FETCH attendees.personalInformation personalInformation WHERE fellowship.id =:parameterFellowshipId");

		query.setParameter("parameterFellowshipId", id);

		return (Fellowship) query.getSingleResult();

	}

	@Override
	public Fellowship get(Long id) {

		return fellowshipDao.getOne(id);
	}

	@Override

	public ArrayList<AttendeePresentationOnGrid> getPresentationOfAttendeesList(Long id) {

		ArrayList<AttendeePresentationOnGrid> attendeePresentationOnGridAggregate = new ArrayList<AttendeePresentationOnGrid>();
		for (Attendee attendee : fellowshipDao.getOne(id).getAttendees()) {

			// System.out.println(attendeeService.get(attendee.getId()).getPersonalInformation().getEmailAddress());
			PersonalInformation personalInformation = attendee.getPersonalInformation();
			AttendeePresentationOnGrid attendeePresentationOnGridrow = new AttendeePresentationOnGrid();
			attendeePresentationOnGridrow.setBirthDate(personalInformation.getBirthDate().toString());
			attendeePresentationOnGridrow.setEmailAddress(attendee.getPersonalInformation().getEmailAddress());
			attendeePresentationOnGridrow
					.setFaceBookAccountURL(attendee.getPersonalInformation().getFaceBookAccountURL());
			attendeePresentationOnGridrow.setId(attendee.getId());
			attendeePresentationOnGridrow.setLastName(attendee.getPersonalInformation().getLastName());
			attendeePresentationOnGridrow.setFirstName(attendee.getPersonalInformation().getFirstName());
			attendeePresentationOnGridrow.setMobileNumber(attendee.getPersonalInformation().getMobileNumber());
			attendeePresentationOnGridrow.setSchoolName(attendee.getPersonalInformation().getSchoolName());
			attendeePresentationOnGridAggregate.add(attendeePresentationOnGridrow);

		}
		return attendeePresentationOnGridAggregate;

	}
}
