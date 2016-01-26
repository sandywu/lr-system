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
	public  static final int pageNumber = 10;	//分页页面显示数量
	private static final int addLogIntegral = 2;//添加日记得2积分
	private static final int delLogIntegral = -2;//删除日记扣2积分	
	@Autowired
	LogService logService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PraiseLogService praiseLogService;
	
	/**
	 * 处理写日记的操作
	 * @param log	前台用户输入的内容
	 * @param req	HttpServletRequest
	 * @param attr	RedirectAttributes
	 * @return
	 */
	@RequestMapping(value = "user/me/addLog", method = RequestMethod.POST)
	public String addLog(Log log,HttpServletRequest req,RedirectAttributes attr) {
		System.out.println("====添加日记的标题==="+log.getTitle());
		String whoCreate = ((User)req.getSession().getAttribute("user")).getNickName();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		Date createTime = null;
		try {
			createTime = form.parse(form.format(new Date()));
		} catch (ParseException e) {
			System.out.println("日期格式化错误！======");
			e.printStackTrace();
		}
		log.setCreateTime(createTime);
		log.setWhoCreate(whoCreate);
		
		if(req.getParameter("visible1") == null) {	//所有人可见
			log.setVisible(true);
		} else {		//仅自己可见
			log.setVisible(false);
		}
		if(req.getParameter("reply1") == null) {		//可回复
			log.setReply(true);
		} else {	//不可回复
			log.setReply(false);
		}
		String message;
		if(logService.addLog(log)==1) {
			User us = new User(whoCreate,addLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			message = "发表日记成功！";
		} else {
			message = "发生未知错误，日记发表失败！";
		}
		
		System.out.println("====后log==="+log);
		attr.addFlashAttribute("message", message);
		return "redirect:addlog";
	}
	
	/**
	 * 初始化我的日记的页面
	 * @param thisPage	当前页数
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/myLog", method = RequestMethod.GET)
	public String myLog(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("我是初始化我的日记页面的=============页数"+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = logService.findAllNumberBywhoCreate(whoCreate);//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
		
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
		model.addAttribute("logs", logs);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		return "user/me/myLog";
	}
	
	/**
	 * 初始化所有日记页面
	 * @param thisPage	当前页数
	 * @param model		
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/allLog", method = RequestMethod.GET)
	public String friendsLog(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("我是初始化所有日记页面的=============页数"+thisPage);
		
		int count = logService.findAllNumberAndNovisible();//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
		
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
		model.addAttribute("logs", logs);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		return "user/friends/allLog";
	}
	
	/**
	 * 初始化好友日记页面
	 * @param thisPage	当前页数
	 * @param whoCreate	好友昵称
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsLog", method = RequestMethod.GET)
	public String allLog(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model) {
		int count = logService.findNumberAndNovisibleByWhocreate(whoCreate);//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
		
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
		model.addAttribute("logs", logs);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		System.out.println("我是初始化好友日记页面的====总条数："+count+"=========当前页"+thisPage+"，总页数："+pageSize);
		return "user/friends/friendsLog";
	}
	
	/**
	 * 处理删除日记的操作
	 * @param id		被删除日记的id
	 * @param thisPage	当前页数
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/delLog", method = RequestMethod.GET)
	public String delLogById(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=删除log的id是："+id+"，当前页数是："+thisPage);
		if(logService.delLogById(id) == 1) {
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			replyService.delReplyBylogId(id);//级联删除回复信息
			praiseLogService.delAllByLogId(id);//级联删除点赞信息
		}
		
		return "redirect:allLog?thisPage="+thisPage;
	}
	
	/**
	 * 处理删除日记的操作
	 * @param id		日记的id
	 * @param thisPage	当前页数
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/focusDelLog", method = RequestMethod.GET)
	public String focusDelLog(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=删除log的id是："+id+"，当前页数是："+thisPage);
		if(logService.delLogById(id) == 1) {	
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			replyService.delReplyBylogId(id);//级联删除回复信息
			praiseLogService.delAllByLogId(id);//级联删除点赞信息
		}
		
		return "redirect:focusLog?thisPage="+thisPage;
	}
	
	/**
	 * 处理删除日记的操作
	 * @param id	被删除日记的id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/indexDelLog", method = RequestMethod.GET)
	public String indexDelLog(@RequestParam("logId")int id,
			HttpSession session) {
		System.out.println("===---=删除log的id是："+id);
		if(logService.delLogById(id) == 1) {	
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			replyService.delReplyBylogId(id);//级联删除回复信息
			praiseLogService.delAllByLogId(id);//级联删除点赞信息
		}
		
		return "redirect:index";
	}
	
	/**
	 * 处理删除日记的操作
	 * @param id		被删除日记的id
	 * @param thisPage	当前页数
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/delLog", method = RequestMethod.GET)
	public String delLogById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=删除log的id是："+id+"，当前页数是："+thisPage);
		String message = "";
		if(logService.delLogById(id)==1) {
			User us = new User(((User)session.getAttribute("user")).getNickName(),delLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			replyService.delReplyBylogId(id);//级联删除所有回复信息
			praiseLogService.delAllByLogId(id);//级联删除点赞信息
		} else {
			message = "发生未知错误，删除日记失败！";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:myLog?thisPage="+thisPage;
	}
	
	/**
	 * 处理删除日记的操作
	 * @param id		被删除日记的id
	 * @param thisPage	当前页数
	 * @param blogName	日记本名
	 * @param whoCreate 日记创建人
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delLogs", method = RequestMethod.GET)
	public String delLogByIds(@RequestParam("logId")int id,
			@RequestParam("thisPage")int thisPage,
			@RequestParam("blogName")String blogName,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("===---=删除log的id是："+id+"，当前页数是："+thisPage+",日记本："+blogName+"，创建人："+whoCreate);
		String message = "";
		if(logService.delLogById(id)==1) {
			User us = new User(whoCreate,delLogIntegral);
			userService.updateIntegral(us);	//更新用户积分
			replyService.delReplyBylogId(id);//级联删除所有回复信息
			praiseLogService.delAllByLogId(id);//级联删除点赞信息
		} else {
			message = "发生未知错误，删除日记失败！";
		}
		attr.addFlashAttribute("message", message);
		
		attr.addAttribute("blogName", blogName);
		attr.addAttribute("whoCreate", whoCreate);
		return "redirect:blogInfo?thisPage="+thisPage;
	}
	
}
