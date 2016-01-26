package com.bqq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.ReportLog;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class ReportLogService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int checkReport(ReportLog reportLog) {
		return queryDao.executeForObject("ReportLog.checkReport", reportLog, Integer.class);
	}
	
	public int addReport(ReportLog reportLog) {
		return updateDao.execute("ReportLog.addReport", reportLog);
	}
}
