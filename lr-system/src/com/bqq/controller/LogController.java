package com.bqq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bqq.domain.Log;
import com.bqq.domain.User;
import com.bqq.service.LogService;
import com.bqq.service.PraiseLogService;
import com.bqq.service.ReplyService;
import com.bqq.service.UserService;
import com.bqq.util.TodoPage;
import com.bqq.web.Logs;

@Controller
public class LogController {
	public  static final int pageNumber = 10;	//��ҳҳ����ʾ����
	private static final int addLogIntegral = 2;//����ռǵ�2����
	private static final int delLogIntegral = -2;//ɾ���ռǿ�2����	
	@Autowired
	LogService logService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PraiseLogService praiseLogService;
	
	/**
	 * ����д�ռǵĲ���
	 * @param log	ǰ̨�û����������
	 * @param req	HttpServletRequest
	 * @param attr	RedirectAttributes
	 * @return
	 */
	@RequestMapping(value = "user/me/addLog", method = RequestMethod.POST)
	public String addLog(Log log,HttpServletRequest req,RedirectAttributes attr) {
		System.out.println("====����ռǵı���==="+log.getTitle());
		String whoCreate = ((User)req.getSession().getAttribute("user")).getNickName();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		Date createTime = null;
		try {
			createTime = form.parse(form.format(new Date()));
		} catch (ParseException e) {
			System.out.println("���ڸ�ʽ������======");
			e.printStackTrace();
		}
		log.setCreateTime(createTime);
		log.setWhoCreate(whoCreate);
		
		if(req.getParameter("visible1") == null) {	//�����˿ɼ�
			log.setVisible(true);
		} else {		//���Լ��ɼ�
			log.setVisible(false);
		}
		if(req.getParameter("reply1") == null) {		//�ɻظ�
			log.setReply(true);
		} else {	//���ɻظ�
			log.setReply(false);
		}
		String message;
		if(logService.addLog(log)==1) {
			User us = new User(whoCreate,addLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			message = "�����ռǳɹ���";
		} else {
			message = "����δ֪�����ռǷ���ʧ�ܣ�";
		}
		
		System.out.println("====��log==="+log);
		attr.addFlashAttribute("message", message);
		return "redirect:addlog";
	}
	
	/**
	 * ��ʼ���ҵ��ռǵ�ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/myLog", method = RequestMethod.GET)
	public String myLog(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ���ҵ��ռ�ҳ���=============ҳ��"+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = logService.findAllNumberBywhoCreate(whoCreate);//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		
		TodoPage page = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Log> allLog = logService.findBywhoCreate(page);
		int replyNumber = 0;
		List<Logs> logs = new ArrayList<Logs>();
		for(Log lg : allLog) {
			replyNumber = replyService.findAllNumberBylogId(lg.getId());
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyNumber,praiseLogService.findNumberByLogId(lg.getId()));
			logs.add(log);
		}
		model.addAttribute("logs", logs);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		return "user/me/myLog";
	}
	
	/**
	 * ��ʼ�������ռ�ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param model		
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/allLog", method = RequestMethod.GET)
	public String friendsLog(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ�������ռ�ҳ���=============ҳ��"+thisPage);
		
		int count = logService.findAllNumberAndNovisible();//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		
		TodoPage page = new TodoPage(null, (thisPage-1)*pageNumber, pageNumber);
		List<Log> allLog = logService.findAll(page);
		int replyNumber = 0;
		List<Logs> logs = new ArrayList<Logs>();
		for(Log lg : allLog) {
			replyNumber = replyService.findAllNumberBylogId(lg.getId());
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyNumber,praiseLogService.findNumberByLogId(lg.getId()));
			String userPhoto = userService.findImageByNickname(lg.getWhoCreate());
			log.setUserPhoto(userPhoto);
			logs.add(log);
		}
		model.addAttribute("logs", logs);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		return "user/friends/allLog";
	}
	
	/**
	 * ��ʼ�������ռ�ҳ��
	 * @param thisPage	��ǰҳ��
	 * @param whoCreate	�����ǳ�
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsLog", method = RequestMethod.GET)
	public String allLog(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model) {
		int count = logService.findNumberAndNovisibleByWhocreate(whoCreate);//������
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//��ҳ��
		
		TodoPage page = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Log> allLog = logService.findAllByWhocreate(page);
		int replyNumber = 0;
		List<Logs> logs = new ArrayList<Logs>();
		for(Log lg : allLog) {
			replyNumber = replyService.findAllNumberBylogId(lg.getId());
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyNumber,praiseLogService.findNumberByLogId(lg.getId()));
			String userPhoto = userService.findImageByNickname(lg.getWhoCreate());
			log.setUserPhoto(userPhoto);
			logs.add(log);
		}
		model.addAttribute("logs", logs);	//��ҳ��¼��Ϣ
		model.addAttribute("thisPage", thisPage);	//��ǰҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��

		System.out.println("���ǳ�ʼ�������ռ�ҳ���====��������"+count+"=========��ǰҳ"+thisPage+"����ҳ����"+pageSize);
		return "user/friends/friendsLog";
	}
	
	/**
	 * ����ɾ���ռǵĲ���
	 * @param id		��ɾ���ռǵ�id
	 * @param thisPage	��ǰҳ��
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/delLog", method = RequestMethod.GET)
	public String delLogById(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=ɾ��log��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		if(logService.delLogById(id) == 1) {
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			replyService.delReplyBylogId(id);//����ɾ���ظ���Ϣ
			praiseLogService.delAllByLogId(id);//����ɾ��������Ϣ
		}
		
		return "redirect:allLog?thisPage="+thisPage;
	}
	
	/**
	 * ����ɾ���ռǵĲ���
	 * @param id		�ռǵ�id
	 * @param thisPage	��ǰҳ��
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/focusDelLog", method = RequestMethod.GET)
	public String focusDelLog(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=ɾ��log��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		if(logService.delLogById(id) == 1) {	
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			replyService.delReplyBylogId(id);//����ɾ���ظ���Ϣ
			praiseLogService.delAllByLogId(id);//����ɾ��������Ϣ
		}
		
		return "redirect:focusLog?thisPage="+thisPage;
	}
	
	/**
	 * ����ɾ���ռǵĲ���
	 * @param id	��ɾ���ռǵ�id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/indexDelLog", method = RequestMethod.GET)
	public String indexDelLog(@RequestParam("logId")int id,
			HttpSession session) {
		System.out.println("===---=ɾ��log��id�ǣ�"+id);
		if(logService.delLogById(id) == 1) {	
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			replyService.delReplyBylogId(id);//����ɾ���ظ���Ϣ
			praiseLogService.delAllByLogId(id);//����ɾ��������Ϣ
		}
		
		return "redirect:index";
	}
	
	/**
	 * ����ɾ���ռǵĲ���
	 * @param id		��ɾ���ռǵ�id
	 * @param thisPage	��ǰҳ��
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/delLog", method = RequestMethod.GET)
	public String delLogById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=ɾ��log��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		String message = "";
		if(logService.delLogById(id)==1) {
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			replyService.delReplyBylogId(id);//����ɾ�����лظ���Ϣ
			praiseLogService.delAllByLogId(id);//����ɾ��������Ϣ
		} else {
			message = "����δ֪����ɾ���ռ�ʧ�ܣ�";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:myLog?thisPage="+thisPage;
	}
	
	/**
	 * ����ɾ���ռǵĲ���
	 * @param id		��ɾ���ռǵ�id
	 * @param thisPage	��ǰҳ��
	 * @param blogName	�ռǱ���
	 * @param whoCreate �ռǴ�����
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delLogs", method = RequestMethod.GET)
	public String delLogByIds(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			@RequestParam("blogName")String blogName,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("===---=ɾ��log��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage+",�ռǱ���"+blogName+"�������ˣ�"+whoCreate);
		String message = "";
		if(logService.delLogById(id)==1) {
			User us = new User(whoCreate,delLogIntegral);
			userService.updateIntegral(us);	//�����û�����
			replyService.delReplyBylogId(id);//����ɾ�����лظ���Ϣ
			praiseLogService.delAllByLogId(id);//����ɾ��������Ϣ
		} else {
			message = "����δ֪����ɾ���ռ�ʧ�ܣ�";
		}
		attr.addFlashAttribute("message", message);
		
		attr.addAttribute("blogName", blogName);
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:blogInfo?thisPage="+thisPage;
	}
	
}
