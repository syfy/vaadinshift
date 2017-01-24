package com.project.el.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.el.domain.PersonalInformation;
@Transactional
public interface PersonalInformationDAO extends JpaRepository<PersonalInformation,Long> {

}
