package com.bqq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Focus;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class FocusService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;

	public int checkFocus(Focus focus) {
		return queryDao.executeForObject("Focus.checkFocus", focus, Integer.class);
	}

	public int focusOne(Focus focus) {
		return updateDao.execute("Focus.focusOne", focus);
	}

	public int cancelFocus(Focus focus) {
		return updateDao.execute("Focus.cancelFocus", focus);
	}

	public List<Integer> findMeFocus(int whoFocuId) {
		return queryDao.executeForObjectList("Focus.findMeFocus", whoFocuId);
	}

	public List<Integer> findFocusMe(int whoFocuId) {
		return queryDao.executeForObjectList("Focus.findFocusMe", whoFocuId);
	}

	

}
