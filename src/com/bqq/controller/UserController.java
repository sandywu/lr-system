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
	 * ajax检测注册邮箱
	 * @param email	注册邮箱
	 * @return
	 */
	@RequestMapping(value = "checkEmail", method = RequestMethod.GET)
	public @ResponseBody int checkEmail(@RequestParam("email") String email) {
		System.out.println("我是处理ajax检测邮箱是否存在的控制层=============================");
		return userService.checkEmail(email);
	}
	
	/**
	 * ajax检测注册昵称
	 * @param nickName	注册昵称
	 * @return
	 */
	@RequestMapping(value = {"checkNickName", "user/me/checkNickName"}, method = RequestMethod.GET)
	public @ResponseBody int checkNickName(@RequestParam("nickName") String nickName) {
		System.out.println("我是处理ajax检测昵称是否存在的控制层=============================");
		return userService.checkNickName(nickName);
	}
	
	/**
	 * 处理注册新用户功能
	 * @param user	前台表单填写的邮箱和昵称信息
	 * @param model	模型
	 * @param attr	重定向时传数据的存储域
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user,
			@RequestParam("qeustion")String qeustion,
			@RequestParam("answer")String answer,Model model,
			RedirectAttributes attr,HttpSession session) {
		System.out.println("我是处理用户注册的控制层---------------------------------");
		String message ;
		if(userService.checkEmail(user.getEmail()) == 1) {	//邮箱已被注册
			message = new String("注册失败！该邮箱已被注册，请换个邮箱试试！");
		} else if(userService.checkNickName(user.getNickName()) == 1) {	//昵称已被占用
			message = new String("注册失败！该昵称已被占用，请换个昵称试试！");
		} else {	//该邮箱和昵称可用
			user.setCreateTime(new Date());	//注册日期
			user.setIsAdmin(false);	//注册用户不是管理员
			user.setState("1");		//注册用户状态为可用状态
			user.setIntegral(0);	//注册用户积分为0
			System.out.println("二维码名："+(user.getEmail().split("@"))[0]);
			user.setQrCode((user.getEmail().split("@"))[0]+".jpg");//用户二维码为邮箱前缀
			UUID uuid = UUID.randomUUID(); //随机生成的32位UUID 
			String password = uuid.toString().split("-")[0];//未MD5的密码
			System.out.println("未处理的密码："+password);
			//将用户注册密码进行MD5处理后保存数据库
	        user.setPassword(GetMD5.getMD5(uuid.toString().split("-")[0]));  //UUID的前8位为注册用户密码
	        if(userService.addUser(user) == 0) {	//注册失败
	        	message = "抱歉！发生未知错误，注册失败！ ==！";
	        }else{
	        	// TODO 发送邮件，解开注释后需要将个人163邮箱信息填写完善，并确保邮箱开通该服务
		        //SendEmail.send(user.getEmail(), user.getNickName(), password);
	        	Blog blog = new Blog(user.getNickName(), "默认日记");//为用户创建默认日记本
	        	blogService.addBlog(blog);
	        	Retrieve retrieve = new Retrieve(user.getEmail(),qeustion,answer);
	        	retrieveService.addOne(retrieve);//保存用户找回密码
	        	//content:二维码内容信息，srcImagePath：中间图片地址，destImagePath：生成二维码地址
	        	QrCode.encode(GetContent.getContent(user),
	        	session.getServletContext().getRealPath("photo")+"\\system.jpg",
	        	session.getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//为用户生成二维码
	        	message = "恭喜您！注册新账号成功，快去注册邮箱查看密码登录吧。";
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
		String message = userService.updatePwd(user)==1 ? "更新密码成功！" : "发生未知错误，更新密码失败";
		
		attr.addFlashAttribute("message", message);
		return "redirect:findPwd";
	}

	/**
	 * 处理用户登录功能
	 * @param user	前台页面用户填写的邮箱和密码
	 * @param session	session
	 * @param attr	用户重定向时传输数据的存储域
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String checkLogin(User user, HttpSession session, RedirectAttributes attr) {
		System.out.println("我是处理用户登录的控制层---------------------------------");
		user.setPassword(GetMD5.getMD5(user.getPassword()));//将用户输入密码进行MD5处理
		String message;
		if(userService.checkLoginPassword(user) == 1) {	//密码正确
			User user2 = userService.findOneByEmail(user.getEmail());
			if(user2.getState().equals("0")) {
				message = new String("登录失败！该账号已被封，请及时联系管理员！管理员邮箱：15620608572@163.com");
				attr.addFlashAttribute("message", message);
				return "redirect:/";
			}
			session.setAttribute("user", user2);	//将用户信息保存到session中
			return user2.getIsAdmin() ? "redirect:admin/admin" : "redirect:user/me/index";
		}
		message = new String("登录失败！账号密码错误，请重新登录！");
		attr.addFlashAttribute("message", message);
		return "redirect:/";
	}
	
	/**
	 * 处理用户退出账号功能
	 * @param session	session
	 * @return
	 */
	@RequestMapping(value = {"user/me/quit", "admin/quit"}, method = RequestMethod.GET)
	public String quit(HttpSession session) {
		System.out.println("我是处理用户退出账号的控制层=============================");
		session.invalidate();	//将session中数据摧毁
		return "redirect:/";
	}
	
	/**
	 * 修改资料处理功能
	 * @param user 前台页面用户填写额昵称、个性签名
	 * @param req HttpServletRequest
	 * @param attr RedirectAttributes 重定向时存储数据
	 * @return
	 */
	@RequestMapping(value = "user/me/updateMe", method = RequestMethod.POST)
	public String updateMe(User user,
			HttpServletRequest req,
			RedirectAttributes attr) {
		System.out.println(req.getParameter("describ")+"======"+user.getNickName()+"="+user.getSignature()+"=========处理修改个人账号信息=====++++++++++++++++++");
		String oldNickname = ((User)req.getSession().getAttribute("user")).getNickName();
		String newNickname = user.getNickName();
		user.setEmail(((User)req.getSession().getAttribute("user")).getEmail());
		user.setCreateTime(((User)req.getSession().getAttribute("user")).getCreateTime());
		user.setIsAdmin(((User)req.getSession().getAttribute("user")).getIsAdmin());
		user.setDescrib(req.getParameter("describ"));	//
		
		String email = ((User)req.getSession().getAttribute("user")).getEmail();	//获取email
		user.setEmail(email);	//根据用户的邮箱（唯一标识符）修改账号信息
		
		String message ;
		if(userService.updateMe(user) == 1) {
			if(!oldNickname.equals(newNickname)) {	//用户昵称改变
				UpdateNickname updateNickname = new UpdateNickname(oldNickname, newNickname);
				blogService.updateWhocreate(updateNickname);//更新日记本的创建人
				logService.updateWhocreate(updateNickname);//更新日记的创建人
				saidService.updateWhocreate(updateNickname);//更行说说的创建人
				todoService.updateWhocreate(updateNickname);//更新TODO的创建人
			}
			if(user.getImage() !=null ) {
				//content:二维码内容信息，srcImagePath：中间图片地址，destImagePath：生成二维码地址
	        	QrCode.encode(GetContent.getContent(user),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+user.getImage(),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//为用户生成二维码
			}else{
				//content:二维码内容信息，srcImagePath：中间图片地址，destImagePath：生成二维码地址
	        	QrCode.encode(GetContent.getContent(user),
	        	req.getSession().getServletContext().getRealPath("photo")+"\\system.jpg",
	        	req.getSession().getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//为用户生成二维码
			}
			message = "恭喜！成功修改资料！";
		}else{
			message = "由于未知原因，修改资料失败！万分抱歉 ~~";
		}
		
		attr.addFlashAttribute("message", message);	//返回提示消息
		req.getSession().invalidate();			//摧毁session
		req.getSession().setAttribute("user", userService.findOneByEmail(email));	//重新保存user对象到session中
		return "redirect:account";	//重定向到个人账号页面
	}
	
	/**
	 * 处理用户上传头像
	 * @param image 用户选择上传的图片
	 * @param session session
	 * @param attr RedirectAttributes重定向时存储数据
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
			String path = session.getServletContext().getRealPath("photo");	//获取服务器的路径信息
			System.out.println(path+"==========保存图片的路径=======================");
			
			File file=new File(path,image.getOriginalFilename());	//获取上传图片的真实名
			
			UploadImg.validateImage(image);	//验证图片格式
			User user = (User) session.getAttribute("user");
			
			user.setImage(image.getOriginalFilename());	//数据库保存的图片名
			userService.uploadImg(user);
			if(!file.exists()) {	//判断该文件是否存在
				file.mkdirs();	
			}
			image.transferTo(file);
			
			//content:二维码内容信息，srcImagePath：中间图片地址，destImagePath：生成二维码地址
        	QrCode.encode(GetContent.getContent(user),
        	session.getServletContext().getRealPath("photo")+"\\"+user.getImage(),
        	session.getServletContext().getRealPath("photo")+"\\"+(user.getEmail().split("@"))[0]+".jpg");//为用户生成二维码
			message = "头像修改成功！";
		}else{
			message = "请选择需要上传的图片";
		}
		attr.addFlashAttribute("message", message);	//返回提示消息
		return "redirect:account";	//重定向到个人账号页面
	}
	
	/**
	 * 处理用户修改密码
	 * @param oldPwd	旧密码
	 * @param newPwd	新密码
	 * @param session	session
	 * @param attr	重定向存储域
	 * @return	重定向到的页面
	 */
	@RequestMapping(value = "user/me/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@RequestParam("oldPwd")String oldPwd,
			@RequestParam("newPwd")String newPwd,
			HttpSession session,
			RedirectAttributes attr) {
		System.err.println("旧密码是："+oldPwd+"-----新密码是："+newPwd+"==========");
		User user = (User) session.getAttribute("user");
		user.setPassword(GetMD5.getMD5(oldPwd));
		String message = null;
		if(userService.checkLoginPassword(user) == 1) {	//旧密码正确，可以修改
			user.setPassword(GetMD5.getMD5(newPwd));
			message = userService.updatePwd(user) == 1 ? "恭喜您，密码修改成功！" : "抱歉，发生未知错误，修改密码失败！ ==！";
		} else {
			message = "旧密码错误，修改密码失败！";
		}
		attr.addFlashAttribute("message", message);
		return "redirect:account";	//重定向到个人账号页面
	}
	
	@RequestMapping(value = "user/me/active", method = RequestMethod.GET)
	public String active(Model model) {
		List<User> users = userService.findAllByIntegral();//按积分排序所有用户
		List<Focuses> focuses = new ArrayList<Focuses>();
		Focuses focus;
		for(User us : users) {
			focus = new Focuses(null,us.getId(),us.getNickName(),us.getImage(),us.getIntegral());
			focuses.add(focus);
		}
		
		model.addAttribute("title", "所有用户");//标题
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
