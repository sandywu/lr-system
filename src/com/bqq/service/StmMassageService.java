package com.bqq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.StmMassage;
import com.bqq.util.Page;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class StmMassageService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;

	public int findAllNumber() {
		return queryDao.executeForObject("StmMassage.findAllNumber", null, Integer.class);
	}

	public List<StmMassage> findNumbByPage(Page page) {
		return queryDao.executeForObjectList("StmMassage.findNumbByPage", page);
	}

	public void addStmMsg(StmMassage stmMassage) {
		updateDao.execute("StmMassage.addStmMsg", stmMassage);
	}

	public void updateState(StmMassage stmMassage) {
		updateDao.execute("StmMassage.updateState", stmMassage);
	}

	public List<StmMassage> findAllNumberByState(int i) {
		return queryDao.executeForObjectList("StmMassage.findAllNumberByState", i);
	}

}
