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
	private static final int addBlogIntegral = 2;//����ռǱ���2����
	private static final int delBlogIntegral = -2;//ɾ���ռǱ���2����
	@Autowired
	BlogService blogService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	UserService userService; 
	
	/**
	 * ����ռǱ�
	 * @param name	�ռǱ���
	 * @param session
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/addBlog", method = RequestMethod.GET)
	public String addBlog(@RequestParam("name")String name,
			HttpSession session,
			RedirectAttributes attr) {
		System.out.println("���ǳ�ʼ������ռǱ��Ŀ��Ʋ�===�ռǱ�����"+name);
		Blog blog = new Blog(((User)session.getAttribute("user")).getNickName(),name);
		String message;
		if(blogService.addBlog(blog) == 1) {
			message = "��ϲ���ɹ�����ռǱ���";
			User user = new User(((User)session.getAttribute("user")).getNickName(),addBlogIntegral);
			userService.updateIntegral(user);	//�����û�����
		}else{
			message = "����δ֪ԭ������ռǱ�ʧ�ܣ���ֱ�Ǹ ~~";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:manageBlog";
	}
	
	/**
	 * ɾ���ռǱ�
	 * @param name �ռǱ���
	 * @param whoCreate	������
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "user/me/delBlog", method = RequestMethod.GET)
	public String addBlog(@RequestParam("name")String name,
			@RequestParam("whoCreate")String whoCreate,
			RedirectAttributes attr) {
		System.out.println("����ɾ���ռǱ��Ŀ��Ʋ㣬Ҫɾ�������֣�"+name+"�������ˣ�"+whoCreate);
		Blog blog = new Blog(whoCreate, name);
		List<Log> logs = logService.findByBlog(blog);
		String message;
		if(blogService.updateName(blog) == 1) {	//�����ռǱ�������
			for(Log log : logs) {
				logService.updateBlogname(log);	//���¸��ռǱ��µ������ռ�
			}
			User user = new User(whoCreate,delBlogIntegral);
			userService.updateIntegral(user);	//�����û�����
			message = "�ɹ�ɾ���ռǱ������Ҹ����˸��ռǱ��������ռ���Ϣ��";
		} else {
			message = "ɾ���ռǱ�ʧ�ܣ�������ɾ����";
		}
		
		attr.addFlashAttribute("message", message);
		return "redirect:manageBlog";
	}
	

	
}
