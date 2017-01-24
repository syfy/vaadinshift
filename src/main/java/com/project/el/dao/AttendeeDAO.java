package com.project.el.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.el.domain.Attendee;
@Transactional
public interface AttendeeDAO extends JpaRepository<Attendee,Long>{

}
