package com.bqq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqq.domain.Focus;
import com.bqq.domain.User;
import com.bqq.service.FocusService;
import com.bqq.service.UserService;
import com.bqq.web.Focuses;

@Controller("FocusController")
public class FocusController {
	private static final int addFocusIntegral = 1;//添加关注得1积分
	private static final int delFocusIntegral = -1;//删除关注扣1积分
	
	@Autowired
	FocusService focusService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 处理关注、取消关注好友
	 * @param friendsId	好友id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/focusFriends", method = RequestMethod.GET)
	public @ResponseBody int focusOne(@RequestParam("friendsId")int friendsId ,
			HttpSession session) {
		System.out.println("被关注人的id是："+friendsId+"，关注人的id:"+((User)session.getAttribute("user")).getId());
		
		Focus focus = new Focus(((User)session.getAttribute("user")).getId(), friendsId);
		if(focusService.checkFocus(focus) == 1){	//已关注，取消关注
			if(focusService.cancelFocus(focus) == 1){
				User user = new User();
				user.setNickName(((User)session.getAttribute("user")).getNickName());
				user.setIntegral(delFocusIntegral);
				userService.updateIntegral(user);	//更新用户积分
				System.out.println("取消关注");
				return 2;	//取消关注成功
			}else{
				return 3;	//取消关注失败
			}
		}else{	//未关注，添加关注
			if(focusService.focusOne(focus) == 1){
				User user = new User();
				user.setNickName(((User)session.getAttribute("user")).getNickName());
				user.setIntegral(addFocusIntegral);
				userService.updateIntegral(user);	//更新用户积分
				System.out.println("添加关注");
				return 1;	//关注成功
			}else{
				return 0;	//关注失败
			}
		}
		
	}
	
	/**
	 * 取消关注
	 * @param friendsId	好友id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/cancelFocus", method = RequestMethod.GET)
	public @ResponseBody int cancelFocus(@RequestParam("friendsId")int friendsId ,
			HttpSession session) {
		System.out.println("被关注人的id是："+friendsId+"，关注人的id:"+((User)session.getAttribute("user")).getId());
		
		Focus focus = new Focus(((User)session.getAttribute("user")).getId(), friendsId);
		if(focusService.cancelFocus(focus) == 1){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 初始化我|好友关注的所有好友
	 * @param id	我|好友id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"user/me/myFocusInfo","user/me/friendsFocusInfo"}, method = RequestMethod.GET)
	public String myFocusInfo(@RequestParam("id")int id,Model model) {
		User use = userService.findOneById(id);
		List<Integer> ids = focusService.findMeFocus(id);//查找所有我关注的人的id
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(Integer id1 : ids) {
			User us = userService.findOneById(id1);
			focus = new Focuses(id,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		model.addAttribute("title", use.getNickName()+" 关注的好友");//标题
		model.addAttribute("focuses", focuses);
		return "user/me/focusInfo";
	} 
	
	/**
	 * 初始化关注我|好友的所有好友
	 * @param id	我|好友的id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"user/me/focusMeInfo","user/me/focusFriendsInfo"}, method = RequestMethod.GET)
	public String focusMeInfo(@RequestParam("id")int id,Model model) {
		User use = userService.findOneById(id);
		List<Integer> ids = focusService.findFocusMe(id);//查找所有关注我的人的id
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(Integer id1 : ids) {
			User us = userService.findOneById(id1);
			focus = new Focuses(id,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		model.addAttribute("title", "关注"+use.getNickName()+"的好友");//标题
		model.addAttribute("focuses", focuses);
		return "user/me/focusInfo";
	} 

}
