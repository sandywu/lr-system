package com.bqq.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqq.domain.PraiseLog;
import com.bqq.domain.User;
import com.bqq.service.PraiseLogService;
import com.bqq.service.UserService;

@Controller("PraiseLogController")
public class PraiseLogController {
	private static final int addPraiseLogIntegral = 1;//�����ռǵ�1����
	private static final int delPraiseLogIntegral = -1;//ɾ�����޿�1����
	@Autowired
	PraiseLogService praiseLogService;
	
	@Autowired
	UserService userService; 
	
	/**
	 * ���ռǵ��޻�ȡ������
	 * @param whoPraiseId	�����˵�id
	 * @param praiseLogId	�����ռǵ�id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/praiseLog", method = RequestMethod.GET)
	public @ResponseBody int praiseLog(@RequestParam("whoPraiseId")int whoPraiseId,
			@RequestParam("praiseLogId")int praiseLogId,
			HttpSession session) {
		PraiseLog praiseLog = new PraiseLog(whoPraiseId,praiseLogId);
		System.out.println("�����˵�id"+whoPraiseId+"�����ռǵ�id"+praiseLogId);
		if(praiseLogService.checkPraised(praiseLog) == 1) {//�Ѿ��޹���
			 praiseLogService.delOne(praiseLog);
			 User user = new User(((User)session.getAttribute("user")).getNickName(),delPraiseLogIntegral);
			 userService.updateIntegral(user);	//�����û�����
			 return 2;
		} else {	//δ�޹�
			if(praiseLogService.addPraise(praiseLog)==1) {
				 User user = new User(((User)session.getAttribute("user")).getNickName(),addPraiseLogIntegral);
				 userService.updateIntegral(user);	//�����û�����
				return 1;
			}
			return  0;
		}
		
	}

}
