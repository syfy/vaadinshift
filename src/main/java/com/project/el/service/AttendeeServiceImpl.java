package com.project.el.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.el.dao.AttendeeDAO;
import com.project.el.domain.Attendee;

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
	public void save(Attendee attendee) {
		// TODO Auto-generated method stub
		attendeeDao.save(attendee);
	}

	@Override
	public Attendee get(Long id) {
		// TODO Auto-generated method stub
	
	//	   return session.getCurrentSession().load(Attendee.class, id);
		return attendeeDao.getOne(id);
	}
	@Override
	public void save(Collection<?> attendees) {
		// TODO Auto-generated method stub
		
		
		//attendeeDao.save(attendees);

		
	}

}
