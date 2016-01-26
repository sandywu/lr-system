package com.bqq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Retrieve;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class RetrieveService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;

	public void addOne(Retrieve retrieve) {
		updateDao.execute("Retrieve.addOne", retrieve);
	}

	public String findQuestionByEmail(String email) {
		return queryDao.executeForObject("Retrieve.findQuestionByEmail", email, String.class);
	}

	public int checkAnswerByEmail(Retrieve retrieve) {
		return queryDao.executeForObject("Retrieve.checkAnswerByEmail", retrieve, Integer.class);
	}
	
}
