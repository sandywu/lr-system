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
	public  static final int pageNumber = 10;	//ҳ����ʾ����
	private static final int addTodoIntegral = 2;//���todo��2����
	private static final int delTodoIntegral = -2;//ɾ��todo��2����
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	UserService userService;
	
	/**
	 * �û����TODO
	 * @param content	����
	 * @param priority	���ȼ�
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
		System.out.println("�û������TODO�����ǣ�"+content+"�����ȼ��ǣ�"+priority);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		
       
		
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
	    System.out.println("��ǰ����: "+form.format(c.getTime()));
	    c.add(Calendar.DAY_OF_MONTH, days);
	    
		Date createTime = null;
		Date endime = null;
		try {
			createTime = form.parse(form.format(new Date()));
			endime = form.parse(form.format(c.getTime()));
		} catch (ParseException e) {
			System.out.println("���ڸ�ʽ������======");
			e.printStackTrace();
		}
		Todo todo = new Todo(whoCreate, content, priority,createTime,endime, false);
		String message;
		if(todoService.addTodo(todo) == 1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),addTodoIntegral);
			userService.updateIntegral(user);	//�����û�����
			message = "���TODO�ɹ���";
		} else {
			message = "��Ǹ������δ֪����todo����ʧ�ܣ������·���һ����! ==!";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:editTODO";	//�ض��򵽱༭todoҳ��
	}
	
	/**
	 * ��ʼ��editTodosҳ�漰��ҳ��ѯ
	 * @param thisPage	��ǰҳ��
	 * @param model	�洢��
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = "user/todo/editTodos", method = RequestMethod.GET)
	public String initEditTodos(@RequestParam("thisPage")int thisPage, Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ��TODO��==============�ڼ�ҳ==========="+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = todoService.findAllNumber(whoCreate);	//������
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//��ҳ��
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//��ǰҳ��ʾ��¼
		
		System.out.println("�ж���ҳ��" + pageSize+"=====��ǰҳ������"+todos.size());	//��ҳ��
		
		model.addAttribute("thisPage", thisPage);	//��ǰ�ڼ�ҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��
		model.addAttribute("todos", todos);	//��ǰҳ��todos
		return "user/todo/editTodos";
	}
	
	/**
	 * ��ʼ��todoҳ��
	 * @param thisPage	��ǰҳ��
	 * @param model	�洢��
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = "user/todo/todo", method = RequestMethod.GET)
	public String initTodo(@RequestParam("thisPage")int thisPage, Model model, HttpSession session) {
		System.out.println("���ǳ�ʼ��TODO��==============�ڼ�ҳ==========="+thisPage);
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		
		int count = todoService.findAllNumber(whoCreate);	//������
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//��ҳ��
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//��ǰҳ��ʾ��¼
		
		System.out.println("�ж���ҳ��" + pageSize+"=====��ǰҳ������"+todos.size());	//��ҳ��
		
		model.addAttribute("thisPage", thisPage);	//��ǰ�ڼ�ҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��
		model.addAttribute("todos", todos);	//��ǰҳ��todos
		return "user/todo/todo";
	}

	/**
	 * ����TODO�����״̬
	 * @param id	TODO��id
	 * @param thisPage	��ǰҳ��
	 * @param attr	�ض���洢��
	 * @return	�ض����ҳ��
	 */
	@RequestMapping(value = {"user/todo/noComplete","user/todo/isComplete"}, method = RequestMethod.GET)
	public String updateNoComplete(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr) {
		System.out.println("����TODO���״̬�����µ�id:"+id+",��ǰҳ�����ǣ�"+thisPage);
		Todo todo = todoService.findOneById(id);
		System.out.println("���ҳ���TODO���״̬��"+todo.getIsComplete());
		todo.setIsComplete(!todo.getIsComplete());	//�����״̬�޸�Ϊ��ֵ
		todoService.updateComplete(todo);
		String message = todoService.updateComplete(todo)==1 ? "" : "����δ֪���󣬸���״̬ʧ�ܣ�";
		
		attr.addFlashAttribute("message", message);
		return "redirect:todo?thisPage="+thisPage;
	}
	
	/**
	 * ����TODO�����ȼ�
	 * @param id	TODO��id
	 * @param priority	���º�����ȼ�
	 * @param thisPage	��ǰ��ʾҳ��
	 * @param attr	�ض���Ĵ洢��
	 * @return	�ض����ҳ��
	 */
	@RequestMapping(value = "user/todo/updatePriority", method = RequestMethod.GET)
	public String updatePriority(@RequestParam("id")int id,
			@RequestParam("priority")int priority,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr) {
		System.out.println("=========���µ�id�ǣ�"+id+",��ǰҳ�ǣ�"+thisPage+"�����µ����ȼ���"+priority);
		Todo todo = new Todo();
		todo.setId(id);
		todo.setPriority(priority);
		String message = todoService.updatePriority(todo)==1 ? "" : "����δ֪���󣬸������ȼ�ʧ�ܣ�";
		
		attr.addFlashAttribute("message", message);
		return "redirect:editTodos?thisPage="+thisPage;
	}
	
	/**
	 * ����ɾ��todo�Ĳ���
	 * @param id		��ɾ��todo��id
	 * @param thisPage	��ǰҳ��
	 * @param attr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/todo/deleteTodo", method = RequestMethod.GET)
	public String delTodoById(@RequestParam("id")int id,
			@RequestParam("thisPage")int thisPage,
			RedirectAttributes attr,
			HttpSession session) {
		System.out.println("===---=ɾ��TODO��id�ǣ�"+id+"����ǰҳ���ǣ�"+thisPage);
		String message;
		if(todoService.delTodoById(id)==1) {
			User user = new User(((User)session.getAttribute("user")).getNickName(),delTodoIntegral);
			userService.updateIntegral(user);	//�����û�����
			message = "";
		} else {
			message = "����δ֪����ɾ��todoʧ�ܣ�";
		}
		
		attr.addFlashAttribute("message", message);
		return "redirect:editTodos?thisPage="+thisPage;
	}

	/**
	 * ��ʼ������todoҳ��
	 * @param thisPage	��ǰҳ��
	 * @param whoCreate	�����ǳ�
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsTodos", method = RequestMethod.GET)
	public String friendsTodos(@RequestParam("thisPage")int thisPage,
			@RequestParam("whoCreate")String whoCreate,
			Model model) {
		System.out.println("���ǳ�ʼ��TODO��==============�ڼ�ҳ==========="+thisPage);
		
		int count = todoService.findAllNumber(whoCreate);	//������
		
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);	//��ҳ��
		
		TodoPage todoPage = new TodoPage(whoCreate, (thisPage-1)*pageNumber, pageNumber);
		List<Todo> todos = todoService.initTodos(todoPage);	//��ǰҳ��ʾ��¼
		
		System.out.println("�ж���ҳ��" + pageSize+"=====��ǰҳ������"+todos.size());	//��ҳ��
		
		model.addAttribute("whoCreate", whoCreate);	//��ǰ�ڼ�ҳ
		model.addAttribute("thisPage", thisPage);	//��ǰ�ڼ�ҳ
		model.addAttribute("pageSize", pageSize);	//��ҳ��
		model.addAttribute("todos", todos);	//��ǰҳ��todos
		return "user/todo/friendsTodos";
	}
}
