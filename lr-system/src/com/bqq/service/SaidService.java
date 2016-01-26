package com.bqq.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Said;
import com.bqq.util.TodoPage;
import com.bqq.util.UpdateNickname;

@Service
public class SaidService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int addSaid(Said said) {
		return updateDao.execute("Said.addSaid", said);
	}

	public int findAllNumberBywhoCreate(String whoCreate) {
		return queryDao.executeForObject("Said.findAllNumberBywhoCreate",whoCreate,Integer.class);
	}
	
	public List<Said> findBywhoCreate(TodoPage page) {
		return queryDao.executeForObjectList("Said.findBywhoCreate", page);
	}

	public int delSaidById(int id) {
		return updateDao.execute("Said.delSaidById", id);
	}

	public int findAllNumber() {
		return queryDao.executeForObject("Said.findAllNumber",null,Integer.class);
	}

	public List<Said> findAll(TodoPage page) {
		return queryDao.executeForObjectList("Said.findAll", page);
	}

	public void updateWhocreate(UpdateNickname updateNickname) {
		updateDao.execute("Said.updateWhocreate", updateNickname);
	}

	public List<Said> findNew15() {
		return queryDao.executeForObjectList("Said.findNew15", null);
	}
	
	
	
}
