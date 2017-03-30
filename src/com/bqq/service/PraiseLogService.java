package com.bqq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.PraiseLog;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class PraiseLogService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;

	public int checkPraised(PraiseLog praiseLog) {
		return queryDao.executeForObject("PraiseLog.checkPraised", praiseLog, Integer.class);
	}

	public int addPraise(PraiseLog praiseLog) {
		return updateDao.execute("PraiseLog.addPraise", praiseLog);
	}

	public int findNumberByLogId(Integer id) {
		return queryDao.executeForObject("PraiseLog.findNumberByLogId", id, Integer.class);
	}

	public void delAllByLogId(int id) {
		updateDao.execute("PraiseLog.delAllByLogId", id);
	}

	public void delOne(PraiseLog praiseLog) {
		updateDao.execute("PraiseLog.delOne", praiseLog);
	}
	
	

}
