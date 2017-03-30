package com.bqq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Blog;
import com.bqq.util.UpdateNickname;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

@Service
public class BlogService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int addBlog(Blog blog) {
		return updateDao.execute("Blog.addBlog", blog);
	}
	
	public List<Blog> findBywhoCreate(String whoCreate) {
		return queryDao.executeForObjectList("Blog.findBywhoCreate", whoCreate);
	}

	public void updateWhocreate(UpdateNickname updateNickname) {
		updateDao.execute("Blog.updateWhocreate", updateNickname);
	}

	public int updateName(Blog blog) {
		return updateDao.execute("Blog.updateName", blog);
	}

	public int findAllNumber() {
		return queryDao.executeForObject("Blog.findAllNumber", null, Integer.class);
	}

}
