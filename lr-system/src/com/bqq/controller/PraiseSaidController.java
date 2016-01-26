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
	private static final int addPraiseSaidIntegral = 1;//点赞说说得1积分
	private static final int delPraiseSaidIntegral = -1;//删除点赞扣1积分
	@Autowired
	PraiseSaidService praiseSaidService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 对说说点赞或取消点赞
	 * @param whoPraiseId	点赞人的id
	 * @param praiseSaidId	点赞说说的id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"user/me/praiseSaid","user/friends/praiseSaid"}, method = RequestMethod.GET)
	public @ResponseBody int praiseLog(@RequestParam("whoPraiseId")int whoPraiseId,
			@RequestParam("praiseSaidId")int praiseSaidId,
			HttpSession session) {
		PraiseSaid praiseSaid = new PraiseSaid(whoPraiseId,praiseSaidId);
		System.out.println("点赞人的id："+whoPraiseId+"，点赞说说的id："+praiseSaidId);
		if(praiseSaidService.checkPraised(praiseSaid) == 1) {//已经赞过了
			User user = new User(((User)session.getAttribute("user")).getNickName(),delPraiseSaidIntegral);
			userService.updateIntegral(user);	//更新用户积分
			praiseSaidService.delOne(praiseSaid);
			return 2;
		} else {	//未赞过
			if(praiseSaidService.addPraise(praiseSaid)==1) {
				User user = new User(((User)session.getAttribute("user")).getNickName(),addPraiseSaidIntegral);
				userService.updateIntegral(user);	//更新用户积分
				return 1;
			}
			return 0;
		}
	}

}
