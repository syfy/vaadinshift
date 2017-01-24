package com.project.el.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.el.dao.PersonalInformationDAO;
import com.project.el.domain.PersonalInformation;
@Service
@Transactional
public class PersonalInformationServiceImpl implements PersonalInformationService {

	@Autowired
	PersonalInformationDAO personalInformationDao;
	
	@Override
	public void save(PersonalInformation personalInformation) {
		// TODO Auto-generated method stub
		personalInformationDao.save(personalInformation);
	}

}
