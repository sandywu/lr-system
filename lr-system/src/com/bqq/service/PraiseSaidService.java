package com.bqq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.PraiseSaid;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class PraiseSaidService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int checkPraised(PraiseSaid praiseSaid) {
		return queryDao.executeForObject("PraiseSaid.checkPraised", praiseSaid, Integer.class);
	}

	public int addPraise(PraiseSaid praiseSaid) {
		return updateDao.execute("PraiseSaid.addPraise", praiseSaid);
	}

	public int findNumberBySaidId(Integer id) {
		return queryDao.executeForObject("PraiseSaid.findNumberBySaidId", id, Integer.class);
	}

	public void delAllBySaidId(int id) {
		updateDao.execute("PraiseSaid.delAllBySaidId", id);
	}

	public void delOne(PraiseSaid praiseSaid) {
		updateDao.execute("PraiseSaid.delOne", praiseSaid);
	}

}
