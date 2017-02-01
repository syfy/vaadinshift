package com.project.el.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.el.dao.AttendeeDAO;
import com.project.el.domain.Attendee;
import com.project.el.domain.Fellowship;

@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService{
	@Autowired
	AttendeeDAO attendeeDao;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	


	
	@Override
	public List<Attendee> findAll() {
		// TODO Auto-generated method stub
		return attendeeDao.findAll();
	}
	
	@Override
	public List<Attendee> findAllJpql() {
		Query query = entityManager.createQuery(
				"SELECT attendee FROM Attendee attendee LEFT JOIN FETCH attendee.personalInformation LEFT JOIN FETCH attendee.attendedFellowships GROUP BY attendee.id");



		return  query.getResultList();
	}

	@Override
	public void save(Attendee attendee) {

		attendeeDao.save(attendee);
	}

	@Override
	public Attendee get(Long id) {

		return attendeeDao.getOne(id);
	}
	@Override
	public void save(Collection<?> attendees) {
		// TODO Auto-generated method stub
		
		
		//attendeeDao.save(attendees);

		
	}

	@Override
	public Attendee getOneJpql(Long id) {

		Query query = entityManager.createQuery(
				"SELECT attendee FROM Attendee attendee LEFT JOIN FETCH attendee.personalInformation WHERE attendee.id =:parameterAttendeeId");

		query.setParameter("parameterAttendeeId", id);

		return (Attendee) query.getSingleResult();

	}

}
