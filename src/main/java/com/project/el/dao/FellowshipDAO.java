package com.project.el.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.el.domain.Fellowship;
@Transactional
public interface FellowshipDAO extends JpaRepository<Fellowship,Long> {

}
