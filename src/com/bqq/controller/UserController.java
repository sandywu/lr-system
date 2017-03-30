package com.bqq.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bqq.domain.Blog;
import com.bqq.domain.Retrieve;
import com.bqq.domain.User;
import com.bqq.service.BlogService;
import com.bqq.service.LogService;
import com.bqq.service.RetrieveService;
import com.bqq.service.SaidService;
import com.bqq.service.TodoService;
import com.bqq.service.UserService;
import com.bqq.util.GetContent;
import com.bqq.util.GetMD5;
import com.bqq.util.QrCode;
import com.bqq.util.SendEmail;
import com.bqq.util.UpdateNickname;
import com.bqq.util.UploadImg;
import com.bqq.web.Focuses;

@Controller("UserController")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	SaidService saidService;
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	RetrieveService retrieveService;
	
	/**
	 * ajax���ע������
	 * @param email	ע������
	 * @return
	 */
	@RequestMapping(value = "checkEmail", method = RequestMethod.GET)
	public @ResponseBody int checkEmail(@RequestParam("email") String email) {
		System.out.println("���Ǵ���ajax��������Ƿ���ڵĿ��Ʋ�=============================");
		return userService.checkEmail(email);
	}
	
	/**
	 * ajax���ע���ǳ�
	 * @param nickName	ע���ǳ�
	 * @return
	 */
	@RequestMapping(value = {"checkNickName", "user/me/checkNickName"}, method = RequestMethod.GET)
	public @ResponseBody int checkNickName(@RequestParam("nickName") String nickName) {
		System.out.println("���Ǵ���ajax����ǳ��Ƿ���ڵĿ��Ʋ�=============================");
		return userService.checkNickName(nickName);
	}
	
	/**
	 * ����ע�����û�����
	 * @param user	ǰ̨����д��������ǳ���Ϣ
	 * @param model	ģ��
	 * @param attr	�ض���ʱ�����ݵĴ洢��
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user,
			@RequestParam("qeustion")String qeustion,
			@RequestParam("answer")String answer,Model model,
			RedirectAttributes attr,HttpSession session) {
		System.out.println("���Ǵ����û�ע��Ŀ��Ʋ�---------------------------------");
		String message ;
		if(userService.checkEmail(user.getEmail()) == 1) {	//�����ѱ�ע��
			message = new String("ע��ʧ�ܣ��������ѱ�ע�ᣬ�뻻���������ԣ�");
		} else if(userService.checkNickName(user.getNickName()) == 1) {	//�ǳ��ѱ�ռ��
			message = new String("ע��ʧ�ܣ����ǳ��ѱ�ռ�ã��뻻���ǳ����ԣ�");
		} else {	//��������ǳƿ���
			user.setCreateTime(new Date());	//ע������
			user.setIsAdmin(false);	//ע���û����ǹ���Ա
			user.setState("1");		//ע���û�״̬Ϊ����״̬
			user.setIntegral(0);	//ע���û�����Ϊ0
			System.out.println("��ά������"+(user.getEmail().split("@"))[0]);
			user.setQrCode((user.getEmail().split("@"))[0]+".jpg");//�û���ά��Ϊ����ǰ׺
			UUID uuid = UUID.randomUUID(); //������ɵ�32λUUID 
			String password = uuid.toString().split("-")[0];//δMD5������
			System.out.println("δ��������룺"+password);
			//���û�ע���������MD5����󱣴����ݿ�
	        user.setPassword(GetMD5.getMD5(uuid.toString().split("-")[0]));  //UUID��ǰ8λΪע���û�����
	        if(userService.addUser(user) == 0) {	//ע��ʧ��
	        	message = "��Ǹ������δ֪����ע��ʧ�ܣ� ==��";
	        }else{
	        	// TODO �����ʼ����⿪ע�ͺ���Ҫ������163������Ϣ��д���ƣ���ȷ�����俪ͨ�÷���
		        //SendEmail.send(user.getEmail(), user.getNickName(), password);
	        	Blog blog = new Blog(user.getNickName(), "Ĭ���ռ�");//Ϊ�û�����Ĭ���ռǱ�
	        	blogService.addBlog(blog);
	        	Retrieve retrieve = new Retrieve(user.getEmail(),qeustion,answer);
	        	retrieveService.addOne(retrieve);//�����û��һ�����
	        	//content:��ά��������Ϣ��srcImagePath���м�ͼƬ��ַ��destImagePath�����ɶ�ά���ַ
	        	QrCode.encode(GetContent.getContent(user),
	        	session.getServletContext().getRealPath("photo")+"\\system.jpg",
	        	session.getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//Ϊ�û����ɶ�ά��
	        	message = "��ϲ����ע�����˺ųɹ�����ȥע������鿴�����¼�ɡ�";
	        }
		}
		attr.addFlashAttribute("message", message);
		return "redirect:register";
	}
	
	@RequestMapping(value = "findPwd", method = RequestMethod.POST)
	public String findPwd(@RequestParam("email")String email,
			@RequestParam("password")String password,
			RedirectAttributes attr) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(GetMD5.getMD5(password));
		String message = userService.updatePwd(user)==1 ? "��������ɹ���" : "����δ֪���󣬸�������ʧ��";
		
		attr.addFlashAttribute("message", message);
		return "redirect:findPwd";
	}

	/**
	 * �����û���¼����
	 * @param user	ǰ̨ҳ���û���д�����������
	 * @param session	session
	 * @param attr	�û��ض���ʱ�������ݵĴ洢��
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String checkLogin(User user, HttpSession session, RedirectAttributes attr) {
		System.out.println("���Ǵ����û���¼�Ŀ��Ʋ�---------------------------------");
		user.setPassword(GetMD5.getMD5(user.getPassword()));//���û������������MD5����
		String message;
		if(userService.checkLoginPassword(user) == 1) {	//������ȷ
			User user2 = userService.findOneByEmail(user.getEmail());
			if(user2.getState().equals("0")) {
				message = new String("��¼ʧ�ܣ����˺��ѱ��⣬�뼰ʱ��ϵ����Ա������Ա���䣺15620608572@163.com");
				attr.addFlashAttribute("message", message);
				return "redirect:/";
			}
			session.setAttribute("user", user2);	//���û���Ϣ���浽session��
			return user2.getIsAdmin() ? "redirect:admin/admin" : "redirect:user/me/index";
		}
		message = new String("��¼ʧ�ܣ��˺�������������µ�¼��");
		attr.addFlashAttribute("message", message);
		return "redirect:/";
	}
	
	/**
	 * �����û��˳��˺Ź���
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = {"user/me/quit", "admin/quit"}, method = RequestMethod.GET)
	public String quit(HttpSession session) {
		System.out.println("���Ǵ����û��˳��˺ŵĿ��Ʋ�=============================");
		session.invalidate();	//��session�����ݴݻ�
		return "redirect:/";
	}
	
	/**
	 * �޸����ϴ�����
	 * @param user ǰ̨ҳ���û���д���ǳơ�����ǩ��
	 * @param req HttpServletRequest
	 * @param attr RedirectAttributes �ض���ʱ�洢����
	 * @return
	 */
	@RequestMapping(value = "user/me/updateMe", method = RequestMethod.POST)
	public String updateMe(User user,
			HttpServletRequest req,
			RedirectAttributes attr) {
		System.out.println(req.getParameter("describ")+"======"+user.getNickName()+"="+user.getSignature()+"=========�����޸ĸ����˺���Ϣ=====++++++++++++++++++");
		String oldNickname = ((User)req.getSession().getAttribute("user")).getNickName();
		String newNickname = user.getNickName();
		user.setEmail(((User)req.getSession().getAttribute("user")).getEmail());
		user.setCreateTime(((User)req.getSession().getAttribute("user")).getCreateTime());
		user.setIsAdmin(((User)req.getSession().getAttribute("user")).getIsAdmin());
		user.setDescrib(req.getParameter("describ"));	//
		
		String email = ((User)req.getSession().getAttribute("user")).getEmail();	//��ȡemail
		user.setEmail(email);	//�����û������䣨Ψһ��ʶ�����޸��˺���Ϣ
		
		String message ;
		if(userService.updateMe(user) == 1) {
			if(!oldNickname.equals(newNickname)) {	//�û��ǳƸı�
				UpdateNickname updateNickname = new UpdateNickname(oldNickname, newNickname);
				blogService.updateWhocreate(updateNickname);//�����ռǱ��Ĵ�����
				logService.updateWhocreate(updateNickname);//�����ռǵĴ�����
				saidService.updateWhocreate(updateNickname);//����˵˵�Ĵ�����
				todoService.updateWhocreate(updateNickname);//����TODO�Ĵ�����
			}
			if(user.getImage() !=null ) {
				//content:��ά��������Ϣ��srcImagePath���м�ͼƬ��ַ��destImagePath�����ɶ�ά���ַ
	        	QrCode.encode(GetContent.getContent(user),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+user.getImage(),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//Ϊ�û����ɶ�ά��
			}else{
				//content:��ά��������Ϣ��srcImagePath���м�ͼƬ��ַ��destImagePath�����ɶ�ά���ַ
	        	QrCode.encode(GetContent.getContent(user),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\system.jpg",
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//Ϊ�û����ɶ�ά��
			}
			message = "��ϲ���ɹ��޸����ϣ�";
		}else{
			message = "����δ֪ԭ���޸�����ʧ�ܣ���ֱ�Ǹ ~~";
		}
		
		attr.addFlashAttribute("message", message);	//������ʾ��Ϣ
		req.getSession().invalidate();			//�ݻ�session
		req.getSession().setAttribute("user", userService.findOneByEmail(email));	//���±���user����session��
		return "redirect:account";	//�ض��򵽸����˺�ҳ��
	}
	
	/**
	 * �����û��ϴ�ͷ��
	 * @param image �û�ѡ���ϴ���ͼƬ
	 * @param session session
	 * @param attr RedirectAttributes�ض���ʱ�洢����
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "user/me/uploadImg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam(value="image",required=false)MultipartFile image,
			HttpSession session,
			RedirectAttributes attr) throws IllegalStateException, IOException{
		String message = null;
		if(!image.isEmpty()) {
			String path = session.getServletContext().getRealPath("photo");	//��ȡ��������·����Ϣ
			System.out.println(path+"==========����ͼƬ��·��=======================");
			
			File file=new File(path,image.getOriginalFilename());	//��ȡ�ϴ�ͼƬ����ʵ��
			
			UploadImg.validateImage(image);	//��֤ͼƬ��ʽ
			User user = (User) session.getAttribute("user");
			
			user.setImage(image.getOriginalFilename());	//���ݿⱣ���ͼƬ��
			userService.uploadImg(user);
			if(!file.exists()) {	//�жϸ��ļ��Ƿ����
				file.mkdirs();	
			}
			image.transferTo(file);
			
			//content:��ά��������Ϣ��srcImagePath���м�ͼƬ��ַ��destImagePath�����ɶ�ά���ַ
        	QrCode.encode(GetContent.getContent(user),
        	session.getServletContext().getRealPath("photo")+"\\"+user.getImage(),
        	session.getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//Ϊ�û����ɶ�ά��
			message = "ͷ���޸ĳɹ���";
		}else{
			message = "��ѡ����Ҫ�ϴ���ͼƬ";
		}
		attr.addFlashAttribute("message", message);	//������ʾ��Ϣ
		return "redirect:account";	//�ض��򵽸����˺�ҳ��
	}
	
	/**
	 * �����û��޸�����
	 * @param oldPwd	������
	 * @param newPwd	������
	 * @param session	session
	 * @param attr	�ض���洢��
	 * @return	�ض��򵽵�ҳ��
	 */
	@RequestMapping(value = "user/me/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@RequestParam("oldPwd")String oldPwd,
			@RequestParam("newPwd")String newPwd,
			HttpSession session,
			RedirectAttributes attr) {
		System.err.println("�������ǣ�"+oldPwd+"-----�������ǣ�"+newPwd+"==========");
		User user = (User) session.getAttribute("user");
		user.setPassword(GetMD5.getMD5(oldPwd));
		String message = null;
		if(userService.checkLoginPassword(user) == 1) {	//��������ȷ�������޸�
			user.setPassword(GetMD5.getMD5(newPwd));
			message = userService.updatePwd(user) == 1 ? "��ϲ���������޸ĳɹ���" : "��Ǹ������δ֪�����޸�����ʧ�ܣ� ==��";
		} else {
			message = "����������޸�����ʧ�ܣ�";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:account";	//�ض��򵽸����˺�ҳ��
	}
	
	@RequestMapping(value = "user/me/active", method = RequestMethod.GET)
	public String active(Model model) {
		List<User> users = userService.findAllByIntegral();//���������������û�
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(User us : users) {
			focus = new Focuses(null,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		
		model.addAttribute("title", "�����û�");//����
		model.addAttribute("focuses", focuses);
		return "user/me/focusInfo";
	}
	
	@RequestMapping(value = {"admin/disable", "admin/available"}, method = RequestMethod.GET)
	public String updataState(@RequestParam("id")int id,@RequestParam("toState")String toState,
			@RequestParam("thisPage")int thisPage) {
		User user = new User();
		user.setId(id);
		user.setState(toState);
		userService.updateState(user);
		
		return "redirect:mannageUser?thisPage="+thisPage;
	}
}
