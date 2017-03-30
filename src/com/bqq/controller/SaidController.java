package com.bqq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bqq.domain.Said;
import com.bqq.domain.User;
import com.bqq.service.PraiseSaidService;
import com.bqq.service.SaidService;
import com.bqq.service.UserService;
import com.bqq.util.TodoPage;
import com.bqq.web.Saids;

@Controller("SaidController")
public class SaidController {
	public  static final int pageNumber = 10;//ҳ����ʾ����
	private static final int addSaidIntegral = 2;//���˵˵��2����
	private static final int delSaidIntegral = -2;//ɾ��˵˵��2����
	@Autowired
	SaidService saidService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PraiseSaidService praiseSaidService;
	
	/**
	 * �������˵˵����
	 * @param content	˵˵������
	 * @param session
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addSaid", method = RequestMethod.GET)
	public String addSaid(@RequestParam("content")String content,
			HttpSession session,
			RedirectAttributes attr) {
		System.out.println("ǰ̨�����˵˵�����ǣ�"+content);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		Date createTime = null;
		try {
			createTime = form.parse(form.format(new Date()));
		} catch (ParseException e) {
			System.out.println("���ڸ�ʽ������======");
			e.printStackTrace();
		}
		Said said = new Said(((User)session.getAttribute("user")).getNickName(),createTime, content);
		System.out.println("˵˵�ķ��������ǣ�"+said.getCreateTime());
		String message = null;
		if(saidService.addSaid(said) == 1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),addSaidIntegral);
			userService.updateIntegral(user);	//�����û�����
		}else{
			message = "��Ǹ������δ֪����˵˵����ʧ�ܣ������·���һ����! ==";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:index";	//�ض��򵽸�����ҳ
	}

	/**
	 * ��ʼ���ҵ�˵˵ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/mySaid", method = RequestMethod.GET)
	public String mySaid(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ������˵˵ҳ���=============ҳ��"+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = saidService.findAllNumberBywhoCreate(whoCreate);//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		TodoPage page = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Said> sds = saidService.findBywhoCreate(page);
		List<Saids> saids = new ArrayList<Saids>();
		for(Said sd : sds) {
			Saids said = new Saids(sd.getId(), sd.getWhoCreate(), sd.getCreateTime(),
					sd.getContent(), userService.findImageByNickname(sd.getWhoCreate()),
					praiseSaidService.findNumberBySaidId(sd.getId()));
			saids.add(said);
		}
		
		model.addAttribute("saids", saids);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		return "user/me/mySaid";
	}
	
	/**
	 * ��ʼ������˵˵ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param whoCreate	�����ǳ�
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsSaid", method = RequestMethod.GET)
	public String friendsSaid(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ������˵˵ҳ���=============ҳ��"+thisPage);
		int count = saidService.findAllNumberBywhoCreate(whoCreate);//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		TodoPage page = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		User friends = userService.findOneByNickname(whoCreate);
		List<Said> sds = saidService.findBywhoCreate(page);
		List<Saids> saids = new ArrayList<Saids>();
		for(Said sd : sds) {
			Saids said = new Saids(sd.getId(), sd.getWhoCreate(), sd.getCreateTime(),
					sd.getContent(), userService.findImageByNickname(sd.getWhoCreate()),
					praiseSaidService.findNumberBySaidId(sd.getId()));
			saids.add(said);
		}
		
		model.addAttribute("friends", friends);	//������ϸ��Ϣ
		model.addAttribute("saids", saids);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		return "user/friends/friendsSaid";
	}
	
	/**
	 * ����ɾ��˵˵�Ĳ���
	 * @param id		��ɾ��˵˵��id
	 * @param thisPage	��ǰҳ��
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/delSaid", method = RequestMethod.GET)
	public String delSaidById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=ɾ��said��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		String message;
		if(saidService.delSaidById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delSaidIntegral);
			userService.updateIntegral(user);	//�����û�����
			message = "";
			praiseSaidService.delAllBySaidId(id);//����ɾ��������Ϣ
		}else{
			message = "����δ֪����ɾ��˵˵ʧ�ܣ�";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:mySaid?thisPage="+thisPage;
	}
	
	/**
	 * ��ʼ������˵˵��ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/allSaid", method = RequestMethod.GET)
	public String allSaid(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ������˵˵ҳ���=============ҳ��"+thisPage);
		
		int count = saidService.findAllNumber();//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		
		TodoPage page = new TodoPage(null, (thisPage-1)*pageNumber, pageNumber);
		List<Said> allSaid = saidService.findAll(page);
		
		List<Saids> saids = new ArrayList<Saids>();
		for(Said sd : allSaid) {
			Saids said = new Saids(sd.getId(), sd.getWhoCreate(), sd.getCreateTime(),
					sd.getContent(), userService.findImageByNickname(sd.getWhoCreate()),
					praiseSaidService.findNumberBySaidId(sd.getId()));
			saids.add(said);
		}
		
		model.addAttribute("saids", saids);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		return "user/friends/allSaid";
	}
	
	/**
	 * ����ɾ��˵˵�Ĳ���
	 * @param id		��ɾ��˵˵��id
	 * @param thisPage	��ǰҳ��
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/delSaid", method = RequestMethod.GET)
	public String delSaidById(@RequestParam("saidId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=ɾ��said��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		if(saidService.delSaidById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delSaidIntegral);
			userService.updateIntegral(user);	//�����û�����
			praiseSaidService.delAllBySaidId(id);//����ɾ��������Ϣ
		}
		
		return "redirect:allSaid?thisPage="+thisPage;
	}
	
	@RequestMapping(value = "admin/mdelSaid", method = RequestMethod.GET)
	public String mdelSaid(@RequestParam("id")int id,@RequestParam("thisPage")int thisPage) {
		System.out.println("===---=ɾ��said��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		Said said = saidService.findOneById(id);
		if(saidService.delSaidById(id)==1) {
			User user = new User(said.getWhoCreate(),delSaidIntegral);
			userService.updateIntegral(user);	//�����û�����
			praiseSaidService.delAllBySaidId(id);//����ɾ��������Ϣ
		}
		
		return "redirect:manageSaid?thisPage="+thisPage;
	}
}
