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
	private static final int addFocusIntegral = 1;//��ӹ�ע��1����
	private static final int delFocusIntegral = -1;//ɾ����ע��1����
	
	@Autowired
	FocusService focusService;
	
	@Autowired
	UserService userService;
	
	/**
	 * �����ע��ȡ����ע����
	 * @param friendsId	����id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/focusFriends", method = RequestMethod.GET)
	public @ResponseBody int focusOne(@RequestParam("friendsId")int friendsId ,
			HttpSession session) {
		System.out.println("����ע�˵�id�ǣ�"+friendsId+"����ע�˵�id:"+((User)session.getAttribute("user")).getId());
		
		Focus focus = new Focus(((User)session.getAttribute("user")).getId(), friendsId);
		if(focusService.checkFocus(focus) == 1){	//�ѹ�ע��ȡ����ע
			if(focusService.cancelFocus(focus) == 1){
				User user = new User();
				user.setNickName(((User)session.getAttribute("user")).getNickName());
				user.setIntegral(delFocusIntegral);
				userService.updateIntegral(user);	//�����û�����
				System.out.println("ȡ����ע");
				return 2;	//ȡ����ע�ɹ�
			}else{
				return 3;	//ȡ����עʧ��
			}
		}else{	//δ��ע����ӹ�ע
			if(focusService.focusOne(focus) == 1){
				User user = new User();
				user.setNickName(((User)session.getAttribute("user")).getNickName());
				user.setIntegral(addFocusIntegral);
				userService.updateIntegral(user);	//�����û�����
				System.out.println("��ӹ�ע");
				return 1;	//��ע�ɹ�
			}else{
				return 0;	//��עʧ��
			}
		}
		
	}
	
	/**
	 * ȡ����ע
	 * @param friendsId	����id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/cancelFocus", method = RequestMethod.GET)
	public @ResponseBody int cancelFocus(@RequestParam("friendsId")int friendsId ,
			HttpSession session) {
		System.out.println("����ע�˵�id�ǣ�"+friendsId+"����ע�˵�id:"+((User)session.getAttribute("user")).getId());
		
		Focus focus = new Focus(((User)session.getAttribute("user")).getId(), friendsId);
		if(focusService.cancelFocus(focus) == 1){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * ��ʼ����|���ѹ�ע�����к���
	 * @param id	��|����id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"user/me/myFocusInfo","user/me/friendsFocusInfo"}, method = RequestMethod.GET)
	public String myFocusInfo(@RequestParam("id")int id,Model model) {
		User use = userService.findOneById(id);
		List<Integer> ids = focusService.findMeFocus(id);//���������ҹ�ע���˵�id
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(Integer id1 : ids) {
			User us = userService.findOneById(id1);
			focus = new Focuses(id,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		model.addAttribute("title", use.getNickName()+" ��ע�ĺ���");//����
		model.addAttribute("focuses", focuses);
		return "user/me/focusInfo";
	} 
	
	/**
	 * ��ʼ����ע��|���ѵ����к���
	 * @param id	��|���ѵ�id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"user/me/focusMeInfo","user/me/focusFriendsInfo"}, method = RequestMethod.GET)
	public String focusMeInfo(@RequestParam("id")int id,Model model) {
		User use = userService.findOneById(id);
		List<Integer> ids = focusService.findFocusMe(id);//�������й�ע�ҵ��˵�id
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(Integer id1 : ids) {
			User us = userService.findOneById(id1);
			focus = new Focuses(id,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		model.addAttribute("title", "��ע"+use.getNickName()+"�ĺ���");//����
		model.addAttribute("focuses", focuses);
		return "user/me/focusInfo";
	} 

}
