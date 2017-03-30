package com.bqq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Blog;
import com.bqq.domain.Log;
import com.bqq.util.Page;
import com.bqq.util.PageForLog;
import com.bqq.util.TodoPage;
import com.bqq.util.UpdateNickname;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class LogService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int addLog(Log log) {
		return updateDao.execute("Log.addLog", log);
	}
	
	public List<Log> findBywhoCreate(TodoPage page) {
		return queryDao.executeForObjectList("Log.findBywhoCreate", page);
	}
	
	public int findSizeByBlogName(Log log) {
		return queryDao.executeForObject("Log.findSizeByBlogName", log, Integer.class);
	}

	public int findAllNumber() {
		return queryDao.executeForObject("Log.findAllNumber", null, Integer.class);
	}

	public int findAllNumberBywhoCreate(String whoCreate) {
		return queryDao.executeForObject("Log.findAllNumberBywhoCreate", whoCreate, Integer.class);
	}
	
	public int delLogById(int id) {
		return updateDao.execute("Log.delLogById", id);
	}

	public List<Log> findAll(TodoPage page) {
		return queryDao.executeForObjectList("Log.findAll", page);
	}

	public int findAllNumberAndNovisible() {
		return queryDao.executeForObject("Log.findAllNumberAndNovisible", null, Integer.class);
	}

	public Log findOneById(int logId) {
		return queryDao.executeForObject("Log.findOneById", logId, Log.class);
	}

	public void updateWhocreate(UpdateNickname updateNickname) {
		updateDao.execute("Log.updateWhocreate", updateNickname);
	}

	public List<Log> findByBlog(PageForLog page) {
		return queryDao.executeForObjectList("Log.findByBlog", page);
	}

	public List<Log> findByBlog(Blog blog) {
		return queryDao.executeForObjectList("Log.findByBlogs", blog);
	}

	public void updateBlogname(Log log) {
		updateDao.execute("Log.updateBlogname", log);
	}

	public int findNumberAndNovisibleByWhocreate(String whoCreate) {
		return queryDao.executeForObject("Log.findNumberAndNovisibleByWhocreate", whoCreate, Integer.class);
	}

	public List<Log> findAllByWhocreate(TodoPage page) {
		return queryDao.executeForObjectList("Log.findAllByWhocreate", page);
	}

	public int findSizeByBlogNameAndNovisible(Log log) {
		return queryDao.executeForObject("Log.findSizeByBlogNameAndNovisible", log, Integer.class);
	}

	public List<Log> findByBlogAndNovisible(PageForLog page) {
		return queryDao.executeForObjectList("Log.findByBlogAndNovisible", page);
	}

	public List<Log> find10ByWhocreate(String nickName) {
		return queryDao.executeForObjectList("Log.find10ByWhocreate", nickName);
	}

	public List<Log> findAllByWhocreate(String nickName) {
		return queryDao.executeForObjectList("Log.findAllByWhocreates", nickName);
	}

	public List<Log> findNew15() {
		return queryDao.executeForObjectList("Log.findNew15", null);
	}

	public List<Log> findNew10AndVisible() {
		return queryDao.executeForObjectList("Log.findNew10AndVisible", null);
	}

	public List<Log> findByIndexPage(Page page) {
		return queryDao.executeForObjectList("Log.findByIndexPage", page);
	}

}
