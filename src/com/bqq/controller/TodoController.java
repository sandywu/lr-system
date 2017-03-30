package com.bqq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.bqq.domain.Todo;
import com.bqq.domain.User;
import com.bqq.service.TodoService;
import com.bqq.service.UserService;
import com.bqq.util.TodoPage;

@Controller("TodoController")
public class TodoController {
	public  static final int pageNumber = 10;	//页面显示数量
	private static final int addTodoIntegral = 2;//添加todo得2积分
	private static final int delTodoIntegral = -2;//删除todo扣2积分
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 用户添加TODO
	 * @param content	内容
	 * @param priority	优先级
	 * @param session	session
	 * @param attr	RedirectAttributes
	 * @return
	 */
	@RequestMapping(value = "user/me/addTodo", method = RequestMethod.GET)
	public String addTodo(@RequestParam("content")String content,
		@RequestParam("days")int days,
		@RequestParam("priority")int priority,
		HttpSession session,
		RedirectAttributes attr) {
		System.out.println("用户输入的TODO内容是："+content+"，优先级是："+priority);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		
       
		
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
	    System.out.println("当前日期: "+form.format(c.getTime()));
	    c.add(Calendar.DAY_OF_MONTH, days);
	    
		Date createTime = null;
		Date endime = null;
		try {
			createTime = form.parse(form.format(new Date()));
			endime = form.parse(form.format(c.getTime()));
		} catch (ParseException e) {
			System.out.println("日期格式化错误！======");
			e.printStackTrace();
		}
		Todo todo = new Todo(whoCreate, content, priority,createTime,endime, false);
		String message;
		if(todoService.addTodo(todo) == 1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),addTodoIntegral);
			userService.updateIntegral(user);	//更新用户积分
			message = "添加TODO成功！";
		} else {
			message = "抱歉，发生未知错误，todo发表失败，请重新发表一个吧! ==!";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:editTODO";	//重定向到编辑todo页面
	}
	
	/**
	 * 初始化editTodos页面及分页查询
	 * @param thisPage	当前页数
	 * @param model	存储域
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = "user/todo/editTodos", method = RequestMethod.GET)
	public String initEditTodos(@RequestParam("thisPage")int thisPage, Model model, HttpSession session) {
		System.out.println("我是初始化TODO的==============第几页==========="+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = todoService.findAllNumber(whoCreate);	//总条数
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//总页数
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//当前页显示记录
		
		System.out.println("有多少页：" + pageSize+"=====当前页条数："+todos.size());	//总页数
		
		model.addAttribute("thisPage", thisPage);	//当前第几页
		model.addAttribute("pageSize", pageSize);	//总页数
		model.addAttribute("todos", todos);	//当前页的todos
		return "user/todo/editTodos";
	}
	
	/**
	 * 初始化todo页面
	 * @param thisPage	当前页数
	 * @param model	存储域
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = "user/todo/todo", method = RequestMethod.GET)
	public String initTodo(@RequestParam("thisPage")int thisPage, Model model, HttpSession session) {
		System.out.println("我是初始化TODO的==============第几页==========="+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		
		int count = todoService.findAllNumber(whoCreate);	//总条数
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//总页数
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//当前页显示记录
		
		System.out.println("有多少页：" + pageSize+"=====当前页条数："+todos.size());	//总页数
		
		model.addAttribute("thisPage", thisPage);	//当前第几页
		model.addAttribute("pageSize", pageSize);	//总页数
		model.addAttribute("todos", todos);	//当前页的todos
		return "user/todo/todo";
	}

	/**
	 * 更新TODO的完成状态
	 * @param id	TODO的id
	 * @param thisPage	当前页数
	 * @param attr	重定向存储域
	 * @return	重定向的页面
	 */
	@RequestMapping(value = {"user/todo/noComplete","user/todo/isComplete"}, method = RequestMethod.GET)
	public String updateNoComplete(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr) {
		System.out.println("更新TODO完成状态，更新的id:"+id+",当前页面数是："+thisPage);
		Todo todo = todoService.findOneById(id);
		System.out.println("查找出的TODO完成状态："+todo.getIsComplete());
		todo.setIsComplete(!todo.getIsComplete());	//将完成状态修改为反值
		todoService.updateComplete(todo);
		String message = todoService.updateComplete(todo)==1 ? "" : "发生未知错误，更新状态失败！";
		
		attr.addFlashAttribute("message", message);
		return "redirect:todo?thisPage="+thisPage;
	}
	
	/**
	 * 更新TODO的优先级
	 * @param id	TODO的id
	 * @param priority	更新后的优先级
	 * @param thisPage	当前显示页数
	 * @param attr	重定向的存储域
	 * @return	重定向的页面
	 */
	@RequestMapping(value = "user/todo/updatePriority", method = RequestMethod.GET)
	public String updatePriority(@RequestParam("id")int id,
			@RequestParam("priority")int priority,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr) {
		System.out.println("=========更新的id是："+id+",当前页是："+thisPage+"，更新的优先级是"+priority);
		Todo todo = new Todo();
		todo.setId(id);
		todo.setPriority(priority);
		String message = todoService.updatePriority(todo)==1 ? "" : "发生未知错误，更新优先级失败！";
		
		attr.addFlashAttribute("message", message);
		return "redirect:editTodos?thisPage="+thisPage;
	}
	
	/**
	 * 处理删除todo的操作
	 * @param id		被删除todo的id
	 * @param thisPage	当前页数
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/todo/deleteTodo", method = RequestMethod.GET)
	public String delTodoById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=删除TODO的id是："+id+"，当前页数是："+thisPage);
		String message;
		if(todoService.delTodoById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delTodoIntegral);
			userService.updateIntegral(user);	//更新用户积分
			message = "";
		} else {
			message = "发生未知错误，删除todo失败！";
		}
		
		attr.addFlashAttribute("message", message);
		return "redirect:editTodos?thisPage="+thisPage;
	}

	/**
	 * 初始化好友todo页面
	 * @param thisPage	当前页数
	 * @param whoCreate	好友昵称
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsTodos", method = RequestMethod.GET)
	public String friendsTodos(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model) {
		System.out.println("我是初始化TODO的==============第几页==========="+thisPage);
		
		int count = todoService.findAllNumber(whoCreate);	//总条数
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//总页数
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//当前页显示记录
		
		System.out.println("有多少页：" + pageSize+"=====当前页条数："+todos.size());	//总页数
		
		model.addAttribute("whoCreate", whoCreate);	//当前第几页
		model.addAttribute("thisPage", thisPage);	//当前第几页
		model.addAttribute("pageSize", pageSize);	//总页数
		model.addAttribute("todos", todos);	//当前页的todos
		return "user/todo/friendsTodos";
	}
}
