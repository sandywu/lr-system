package com.bqq.service;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.User;

@Service
public class UserService {
	
	@Autowired
	QueryDAO queryDao;

	@Autowired
	UpdateDAO updateDao;
	
	public int checkEmail(String email) {
		return queryDao.executeForObject("User.checkEmail", email, Integer.class);
	}
	
	public int checkNickName(String nickName) {
		return queryDao.executeForObject("User.checkNickName", nickName, Integer.class);
	}
	
	public int addUser(User user) {
		return updateDao.execute("User.addUser", user);
	}
	
	public int checkLoginPassword(User user) {
		return queryDao.executeForObject("User.checkPassword", user, Integer.class);
	}

	public User findOneByEmail(String email) {
		return queryDao.executeForObject("User.findOneByEmail", email, User.class);
	}
	
	public int updateMe(User user) {
		return updateDao.execute("User.updateMe", user);
	}
	
	public int uploadImg(User user) {
		return updateDao.execute("User.uploadImg", user);
	}
	
	public int updatePwd(User user) {
		return updateDao.execute("User.updatePwd", user);
	}

	public String findImageByNickname(String whoCreate) {
		return queryDao.executeForObject("User.findImageByNickname", whoCreate, String.class);
	}

	public User findOneByNickname(String whoCreate) {
		return queryDao.executeForObject("User.findOneByNickname", whoCreate, User.class);
	}

	public User findOneById(Integer replyerId) {
		return queryDao.executeForObject("User.findOneById", replyerId, User.class);
	}

	public List<User> find20ByIntegral() {
		return queryDao.executeForObjectList("User.find20ByIntegral", null);
	}

	public int findAllNumber() {
		return queryDao.executeForObject("User.findAllNumber", null, Integer.class);
	}
	
	public int updateIntegral(User user) {
		return updateDao.execute("User.updateIntegral", user);
	}

	public List<User> findAllByIntegral() {
		return queryDao.executeForObjectList("User.findAllByIntegral", null);
	}

}
