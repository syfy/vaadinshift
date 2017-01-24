package com.project.el.service;

import java.util.List;

import com.project.el.domain.Leader;

public interface LeaderService {
	List<Leader> findAll();
	void save(Leader leader);
}
