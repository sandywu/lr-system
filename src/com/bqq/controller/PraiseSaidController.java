package com.bqq.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqq.domain.PraiseSaid;
import com.bqq.domain.User;
import com.bqq.service.PraiseSaidService;
import com.bqq.service.UserService;

@Controller("PraiseSaidController")
public class PraiseSaidController {
	private static final int addPraiseSaidIntegral = 1;//����˵˵��1����
	private static final int delPraiseSaidIntegral = -1;//ɾ�����޿�1����
	@Autowired
	PraiseSaidService praiseSaidService;
	
	@Autowired
	UserService userService;
	
	/**
	 * ��˵˵���޻�ȡ������
	 * @param whoPraiseId	�����˵�id
	 * @param praiseSaidId	����˵˵��id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"user/me/praiseSaid","user/friends/praiseSaid"}, method = RequestMethod.GET)
	public @ResponseBody int praiseLog(@RequestParam("whoPraiseId")int whoPraiseId,
			@RequestParam("praiseSaidId")int praiseSaidId,
			HttpSession session) {
		PraiseSaid praiseSaid = new PraiseSaid(whoPraiseId,praiseSaidId);
		System.out.println("�����˵�id��"+whoPraiseId+"������˵˵��id��"+praiseSaidId);
		if(praiseSaidService.checkPraised(praiseSaid) == 1) {//�Ѿ��޹���
			User user = new User(((User)session.getAttribute("user")).getNickName(),delPraiseSaidIntegral);
			userService.updateIntegral(user);	//�����û�����
			praiseSaidService.delOne(praiseSaid);
			return 2;
		} else {	//δ�޹�
			if(praiseSaidService.addPraise(praiseSaid)==1) {
				User user = new User(((User)session.getAttribute("user")).getNickName(),addPraiseSaidIntegral);
				userService.updateIntegral(user);	//�����û�����
				return 1;
			}
			return 0;
		}
	}

}
