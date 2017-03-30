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
	public  static final int pageNumber = 10;//页面显示数量
	private static final int addSaidIntegral = 2;//添加说说得2积分
	private static final int delSaidIntegral = -2;//删除说说扣2积分
	@Autowired
	SaidService saidService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PraiseSaidService praiseSaidService;
	
	/**
	 * 处理添加说说操作
	 * @param content	说说的内容
	 * @param session
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addSaid", method = RequestMethod.GET)
	public String addSaid(@RequestParam("content")String content,
			HttpSession session,
			RedirectAttributes attr) {
		System.out.println("前台输入的说说内容是："+content);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		Date createTime = null;
		try {
			createTime = form.parse(form.format(new Date()));
		} catch (ParseException e) {
			System.out.println("日期格式化错误！======");
			e.printStackTrace();
		}
		Said said = new Said(((User)session.getAttribute("user")).getNickName(),createTime, content);
		System.out.println("说说的发表日期是："+said.getCreateTime());
		String message = null;
		if(saidService.addSaid(said) == 1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),addSaidIntegral);
			userService.updateIntegral(user);	//更新用户积分
		}else{
			message = "抱歉，发生未知错误，说说发表失败，请重新发表一个吧! ==";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:index";	//重定向到个人首页
	}

	/**
	 * 初始化我的说说页面
	 * @param thisPage	当前页数
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/mySaid", method = RequestMethod.GET)
	public String mySaid(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("我是初始化所有说说页面的=============页数"+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = saidService.findAllNumberBywhoCreate(whoCreate);//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
		TodoPage page = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Said> sds = saidService.findBywhoCreate(page);
		List<Saids> saids = new ArrayList<Saids>();
		for(Said sd : sds) {
			Saids said = new Saids(sd.getId(), sd.getWhoCreate(), sd.getCreateTime(),
					sd.getContent(), userService.findImageByNickname(sd.getWhoCreate()),
					praiseSaidService.findNumberBySaidId(sd.getId()));
			saids.add(said);
		}
		
		model.addAttribute("saids", saids);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		return "user/me/mySaid";
	}
	
	/**
	 * 初始化好友说说页面
	 * @param thisPage	当前页数
	 * @param whoCreate	好友昵称
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsSaid", method = RequestMethod.GET)
	public String friendsSaid(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model, HttpSession session) {
		System.out.println("我是初始化好友说说页面的=============页数"+thisPage);
		int count = saidService.findAllNumberBywhoCreate(whoCreate);//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
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
		
		model.addAttribute("friends", friends);	//好友详细信息
		model.addAttribute("saids", saids);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		return "user/friends/friendsSaid";
	}
	
	/**
	 * 处理删除说说的操作
	 * @param id		被删除说说的id
	 * @param thisPage	当前页数
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/delSaid", method = RequestMethod.GET)
	public String delSaidById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=删除said的id是："+id+"，当前页数是："+thisPage);
		String message;
		if(saidService.delSaidById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delSaidIntegral);
			userService.updateIntegral(user);	//更新用户积分
			message = "";
			praiseSaidService.delAllBySaidId(id);//级联删除点赞信息
		}else{
			message = "发生未知错误，删除说说失败！";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:mySaid?thisPage="+thisPage;
	}
	
	/**
	 * 初始化所有说说的页面
	 * @param thisPage	当前页数
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/allSaid", method = RequestMethod.GET)
	public String allSaid(@RequestParam("thisPage")int thisPage,Model model, HttpSession session) {
		System.out.println("我是初始化所有说说页面的=============页数"+thisPage);
		
		int count = saidService.findAllNumber();//总条数
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数
		
		TodoPage page = new TodoPage(null, (thisPage-1)*pageNumber, pageNumber);
		List<Said> allSaid = saidService.findAll(page);
		
		List<Saids> saids = new ArrayList<Saids>();
		for(Said sd : allSaid) {
			Saids said = new Saids(sd.getId(), sd.getWhoCreate(), sd.getCreateTime(),
					sd.getContent(), userService.findImageByNickname(sd.getWhoCreate()),
					praiseSaidService.findNumberBySaidId(sd.getId()));
			saids.add(said);
		}
		
		model.addAttribute("saids", saids);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数

		return "user/friends/allSaid";
	}
	
	/**
	 * 处理删除说说的操作
	 * @param id		被删除说说的id
	 * @param thisPage	当前页数
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/friends/delSaid", method = RequestMethod.GET)
	public String delSaidById(@RequestParam("saidId")int id,
			@RequestParam("thisPage")int thisPage,
			HttpSession session) {
		System.out.println("===---=删除said的id是："+id+"，当前页数是："+thisPage);
		if(saidService.delSaidById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delSaidIntegral);
			userService.updateIntegral(user);	//更新用户积分
			praiseSaidService.delAllBySaidId(id);//级联删除点赞信息
		}
		
		return "redirect:allSaid?thisPage="+thisPage;
	}
	
	@RequestMapping(value = "admin/mdelSaid", method = RequestMethod.GET)
	public String mdelSaid(@RequestParam("id")int id,@RequestParam("thisPage")int thisPage) {
		System.out.println("===---=删除said的id是："+id+"，当前页数是："+thisPage);
		Said said = saidService.findOneById(id);
		if(saidService.delSaidById(id)==1) {
			User user = new User(said.getWhoCreate(),delSaidIntegral);
			userService.updateIntegral(user);	//更新用户积分
			praiseSaidService.delAllBySaidId(id);//级联删除点赞信息
		}
		
		return "redirect:manageSaid?thisPage="+thisPage;
	}
}
