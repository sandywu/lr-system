package com.bqq.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bqq.domain.Reply;
import com.bqq.domain.User;
import com.bqq.service.ReplyService;
import com.bqq.service.UserService;

@Controller("ReplyController")
public class ReplyController {
	private static final int addReplyIntegral = 1;//��ӻظ���1����
	private static final int delReplyIntegral = -1;//ɾ���ظ���1����
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	UserService userService;
	
	/**
	 * ɾ���ظ���¼
	 * @param replyId	�ظ���¼��id
	 * @param logId		�ظ������ռǵ�id
	 * @param whoCreate	�ռǵĴ�����
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delReply", method = RequestMethod.GET)
	public String delReplyById(@RequestParam("replyId")int replyId,
			@RequestParam("logId")int logId,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("=======ɾ���ظ���id��"+replyId);
		replyService.delReplyById(replyId);
		User user = new User(whoCreate,delReplyIntegral);
		userService.updateIntegral(user);	//�����û�����
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}
	
	/**
	 * ��ӻظ���¼
	 * @param logId		�ظ��ռǵ�id
	 * @param content 	�ظ�����
	 * @param session
	 * @param whoCreate	�ظ��ռǵĴ�����
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addReply", method = RequestMethod.POST)
	public String addReply(@RequestParam("logId")int logId,
			@RequestParam("content")String content,
			HttpSession session,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("=======�ظ����ռǵ�id��"+logId+"�����ݣ�"+content);
		Reply reply = new Reply(logId,((User)session.getAttribute("user")).getId(),content,new Date());
		System.out.println("�ظ���Ϣ�ǣ�"+reply);
		replyService.addReply(reply);
		User user = new User(((User)session.getAttribute("user")).getNickName(),addReplyIntegral);
		userService.updateIntegral(user);	//�����û�����
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}
	
	/**
	 * �Իظ����ٴλظ�
	 * @param logId				�ظ��ռǵ�id
	 * @param content			�ظ�������
	 * @param session	
	 * @param whoCreate			�ظ��ռǵĴ�����
	 * @param replyer2Nickname	�ٴλظ��ı��ظ����ǳ�
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addReply2", method = RequestMethod.POST)
	public String addReply2(@RequestParam("logId")int logId,
			@RequestParam("content")String content,
			HttpSession session,
			@RequestParam("whoCreate")String whoCreate,
			@RequestParam("replyer2Nickname")String replyer2Nickname,
			RedirectAttributes attr) {
		System.out.println("=======�ظ����ռǵ�id��"+logId+"�����ݣ�"+content+"�����ظ��˵��ǳƣ�"+replyer2Nickname);
		Reply reply = new Reply(logId,((User)session.getAttribute("user")).getId(),content,new Date());
		reply.setReplyer2Id(userService.findOneByNickname(replyer2Nickname).getId());
		System.out.println("�ظ���Ϣ�ǣ�"+reply);
		replyService.addReply(reply);
		User user = new User(((User)session.getAttribute("user")).getNickName(),addReplyIntegral);
		userService.updateIntegral(user);	//�����û�����
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}

}
