package com.bqq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bqq.domain.Blog;
import com.bqq.domain.Log;
import com.bqq.domain.User;
import com.bqq.service.BlogService;
import com.bqq.service.LogService;
import com.bqq.service.UserService;

@Controller("BlogController")
public class BlogController {
	private static final int addBlogIntegral = 2;//添加日记本得2积分
	private static final int delBlogIntegral = -2;//删除日记本扣2积分
	@Autowired
	BlogService blogService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	UserService userService; 
	
	/**
	 * 添加日记本
	 * @param name	日记本名
	 * @param session
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addBlog", method = RequestMethod.GET)
	public String addBlog(@RequestParam("name")String name,
			HttpSession session,
			RedirectAttributes attr) {
		System.out.println("我是初始化添加日记本的控制层===日记本名："+name);
		Blog blog = new Blog(((User)session.getAttribute("user")).getNickName(),name);
		String message;
		if(blogService.addBlog(blog) == 1) {
			message = "恭喜！成功添加日记本！";
			User user = new User(((User)session.getAttribute("user")).getNickName(),addBlogIntegral);
			userService.updateIntegral(user);	//更新用户积分
		}else{
			message = "由于未知原因，添加日记本失败！万分抱歉 ~~";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:manageBlog";
	}
	
	/**
	 * 删除日记本
	 * @param name 日记本名
	 * @param whoCreate	创建人
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delBlog", method = RequestMethod.GET)
	public String addBlog(@RequestParam("name")String name,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("我是删除日记本的控制层，要删除的名字："+name+"，创建人："+whoCreate);
		Blog blog = new Blog(whoCreate, name);
		List<Log> logs = logService.findByBlog(blog);
		String message;
		if(blogService.updateName(blog) == 1) {	//更新日记本的名字
			for(Log log : logs) {
				logService.updateBlogname(log);	//更新该日记本下的所有日记
			}
			User user = new User(whoCreate,delBlogIntegral);
			userService.updateIntegral(user);	//更新用户积分
			message = "成功删除日记本，并且更新了该日记本下所有日记信息！";
		} else {
			message = "删除日记本失败，请重新删除！";
		}
		
		attr.addFlashAttribute("message", message);
		return "redirect:manageBlog";
	}
	

	
}
