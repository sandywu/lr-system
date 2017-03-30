package com.bqq.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Reply;

@Service
public class ReplyService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public int findAllNumberBylogId(int logId) {
		return queryDao.executeForObject("Reply.findAllNumberBylogId",logId,Integer.class);
	}

	public List<Reply> findBylogId(int id) {
		System.out.println("查询的日记的id："+id);
		System.out.println("service查询出来的大小是："+queryDao.executeForObjectList("Reply.findBylogId", id).size());
		return queryDao.executeForObjectList("Reply.findBylogId", id);
	}

	public int delReplyById(int replyId) {
		return updateDao.execute("Reply.delReplyById", replyId);
	}

	public void delReplyBylogId(int logId) {
		updateDao.execute("Reply.delReplyBylogId", logId);
	}

	public int addReply(Reply reply) {
		return updateDao.execute("Reply.addReply", reply);
	}
}
