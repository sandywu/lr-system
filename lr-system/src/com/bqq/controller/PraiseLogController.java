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
	private static final int addPraiseLogIntegral = 1;//点赞日记得1积分
	private static final int delPraiseLogIntegral = -1;//删除点赞扣1积分
	@Autowired
	PraiseLogService praiseLogService;
	
	@Autowired
	UserService userService; 
	
	/**
	 * 对日记点赞或取消点赞
	 * @param whoPraiseId	点赞人的id
	 * @param praiseLogId	点赞日记的id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/praiseLog", method = RequestMethod.GET)
	public @ResponseBody int praiseLog(@RequestParam("whoPraiseId")int whoPraiseId,
			@RequestParam("praiseLogId")int praiseLogId,
			HttpSession session) {
		PraiseLog praiseLog = new PraiseLog(whoPraiseId,praiseLogId);
		System.out.println("点赞人的id"+whoPraiseId+"点赞日记的id"+praiseLogId);
		if(praiseLogService.checkPraised(praiseLog) == 1) {//已经赞过了
			 praiseLogService.delOne(praiseLog);
			 User user = new User(((User)session.getAttribute("user")).getNickName(),delPraiseLogIntegral);
			 userService.updateIntegral(user);	//更新用户积分
			 return 2;
		} else {	//未赞过
			if(praiseLogService.addPraise(praiseLog)==1) {
				 User user = new User(((User)session.getAttribute("user")).getNickName(),addPraiseLogIntegral);
				 userService.updateIntegral(user);	//更新用户积分
				return 1;
			}
			return  0;
		}
		
	}

}
