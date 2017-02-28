package com.project.el.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.el.domain.Attendee;
@Transactional
public interface AttendeeDAO extends JpaRepository<Attendee,Long>{
	@Query("SELECT DISTINCT attendee FROM Attendee attendee LEFT JOIN FETCH attendee.personalInformation LEFT JOIN FETCH attendee.attendedFellowships")
	List<Attendee> findAllJpql();
}
