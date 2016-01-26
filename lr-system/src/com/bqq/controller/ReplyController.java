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
	private static final int addReplyIntegral = 1;//添加回复得1积分
	private static final int delReplyIntegral = -1;//删除回复扣1积分
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 删除回复记录
	 * @param replyId	回复记录的id
	 * @param logId		回复所属日记的id
	 * @param whoCreate	日记的创建人
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delReply", method = RequestMethod.GET)
	public String delReplyById(@RequestParam("replyId")int replyId,
			@RequestParam("logId")int logId,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("=======删除回复的id："+replyId);
		replyService.delReplyById(replyId);
		User user = new User(whoCreate,delReplyIntegral);
		userService.updateIntegral(user);	//更新用户积分
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}
	
	/**
	 * 添加回复记录
	 * @param logId		回复日记的id
	 * @param content 	回复内容
	 * @param session
	 * @param whoCreate	回复日记的创建人
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addReply", method = RequestMethod.POST)
	public String addReply(@RequestParam("logId")int logId,
			@RequestParam("content")String content,
			HttpSession session,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("=======回复的日记的id："+logId+"，内容："+content);
		Reply reply = new Reply(logId,((User)session.getAttribute("user")).getId(),content,new Date());
		System.out.println("回复信息是："+reply);
		replyService.addReply(reply);
		User user = new User(((User)session.getAttribute("user")).getNickName(),addReplyIntegral);
		userService.updateIntegral(user);	//更新用户积分
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}
	
	/**
	 * 对回复的再次回复
	 * @param logId				回复日记的id
	 * @param content			回复的内容
	 * @param session	
	 * @param whoCreate			回复日记的创建人
	 * @param replyer2Nickname	再次回复的被回复人昵称
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
		System.out.println("=======回复的日记的id："+logId+"，内容："+content+"，被回复人的昵称："+replyer2Nickname);
		Reply reply = new Reply(logId,((User)session.getAttribute("user")).getId(),content,new Date());
		reply.setReplyer2Id(userService.findOneByNickname(replyer2Nickname).getId());
		System.out.println("回复信息是："+reply);
		replyService.addReply(reply);
		User user = new User(((User)session.getAttribute("user")).getNickName(),addReplyIntegral);
		userService.updateIntegral(user);	//更新用户积分
		
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:logInfo?id="+logId;
	}

}
