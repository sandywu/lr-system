package com.bqq.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bqq.domain.Blog;
import com.bqq.domain.Focus;
import com.bqq.domain.Log;
import com.bqq.domain.Reply;
import com.bqq.domain.Said;
import com.bqq.domain.Todo;
import com.bqq.domain.User;
import com.bqq.service.BlogService;
import com.bqq.service.FocusService;
import com.bqq.service.LogService;
import com.bqq.service.PraiseLogService;
import com.bqq.service.ReplyService;
import com.bqq.service.SaidService;
import com.bqq.service.TodoService;
import com.bqq.service.UserService;
import com.bqq.util.PageForLog;
import com.bqq.web.Blogs;
import com.bqq.web.Focuses;
import com.bqq.web.Logs;
import com.bqq.web.News;
import com.bqq.web.Replys;

@Controller("InitController")
@RequestMapping("/")
public class InitController {
	public  static final int pageNumber = 10;
	@Autowired
	TodoService todoService;

	@Autowired
	BlogService blogService;

	@Autowired
	LogService logService;

	@Autowired
	SaidService saidService;

	@Autowired
	UserService userService;

	@Autowired
	ReplyService replyService;

	@Autowired
	FocusService focusService;

	@Autowired
	PraiseLogService praiseLogService;

	/**
	 * 网站初始化欢迎页面，首页，根路径页面
	 * @param model
	 * @param user	前台获取登录信息
	 * @return
	 */
	@RequestMapping(value = {"/", "index", "welcome"}, method = RequestMethod.GET)
	public String initIndex(Model model, User user) {
		List<Log> lgs = logService.findNew10AndVisible();
		List<Logs> logs = new ArrayList<Logs>();//首页显示日记详情
		int toIndex = lgs.size() > 10 ? 10 : lgs.size();
		for(Log lg : lgs.subList(0, toIndex)) {
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyService.findAllNumberBylogId(lg.getId()),
					praiseLogService.findNumberByLogId(lg.getId()));
			log.setUserPhoto(userService.findImageByNickname(lg.getWhoCreate()));
			logs.add(log);
		}
		List<News> news = new ArrayList<News>();
		News new1;
		List<Todo> todos = todoService.findNew15();//最新todo
		for(Todo todo : todos) {	//将todos保存到News集合中
			new1 = new News(todo.getId(),"todo",todo.getWhoCreate(),todo.getContent(),todo.getCreateTime());
			news.add(new1);
		}
		List<Said> saids = saidService.findNew15();//最新说说
		for(Said said : saids) {	//将saids保存到News集合中
			new1 = new News(said.getId(),"said",said.getWhoCreate(),said.getContent(),said.getCreateTime());
			news.add(new1);
		}
		List<Log> newLogs = logService.findNew15();//最新日记
		for(Log log : newLogs) {	//将newLogs保存到News集合中
			new1 = new News(log.getId(),"log",log.getWhoCreate(),log.getContent(),log.getCreateTime());
			news.add(new1);
		}
		//对news集合按时间排序
		Collections.sort(news); 

		model.addAttribute("userNumber",userService.findAllNumber());//用户数量
		model.addAttribute("blogNumber",blogService.findAllNumber());//日记本数量
		model.addAttribute("logNumber",logService.findAllNumber());	//日记数量
		model.addAttribute("saidNumber",saidService.findAllNumber());//说说数量
		model.addAttribute("todoNumber",todoService.findAllNumber());//TODO数量
		model.addAttribute("logs",logs);	//最新日记，前10
		model.addAttribute("news",news);	//最新动态
		model.addAttribute("actives",userService.find20ByIntegral());//首页需要显示的活跃用户，前20
		return "welcome";
	}

	/**
	 * 新用户注册
	 * @param user	前台获取新用户注册信息
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String initRegister(User user) {

		return "register";
	}
	
	@RequestMapping(value = "findPwd", method = RequestMethod.GET)
	public String findPwd() {
		
		return "findPwd";
	}

	//---------------------------初始化前台页面===============================

	/**
	 * 初始化TODOS页面
	 * @return
	 */
	@RequestMapping(value = "user/me/TODOS", method = RequestMethod.GET)
	public String initTODOS() {
		System.out.println("我是初始化TODOS的控制层=============================");

		return "user/todo/TODOS";
	}

	/**
	 * 初始化editTODO页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/editTODO", method = RequestMethod.GET)
	public String editTODO(Model model, HttpSession session) {
		System.out.println("我是初始化editTODO的控制层=============================");
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		int count = todoService.findAllNumber(whoCreate);	//总条数

		model.addAttribute("count", count);
		return "user/todo/editTODO";
	}

	/**
	 * 初始化index页面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/index", method = RequestMethod.GET)
	public String initIndex(HttpSession session, Model model) {
		System.out.println("我是处理用户首页的控制层=============================");
		int whoFocuId = ((User)session.getAttribute("user")).getId();	//关注人id
		List<Integer> ids = focusService.findMeFocus(whoFocuId);		//所有我关注的好友id
		ids.add(whoFocuId);		//将我的id保存进去
		List<Log> lgs = new ArrayList<Log>();	//保存关注人的前10 日记信息
		for(Integer id : ids) {
			User focused = userService.findOneById(id);
			lgs.addAll(logService.find10ByWhocreate(focused.getNickName()));
		}
		Collections.sort(lgs, new Comparator<Log>(){  
			public int compare(Log l1, Log l2) {  
				//按照日记的id进行降序排列  
				if(l1.getId() > l2.getId()){  
					return -1;  
				}  
				if(l1.getId() == l2.getId()){  
					return 0;  
				}  
				return 1;  
			}  
		}); 
		List<Logs> logs = new ArrayList<Logs>();
		int toIndex = lgs.size()>10 ? 10 : lgs.size();
		for(Log lg : lgs.subList(0, toIndex)) {
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyService.findAllNumberBylogId(lg.getId()),
					praiseLogService.findNumberByLogId(lg.getId()));
			log.setUserPhoto(userService.findImageByNickname(lg.getWhoCreate()));
			logs.add(log);
		}
		//最新动态
		List<News> news = new ArrayList<News>();
		News new1;
		List<Todo> todos = todoService.findNew15();//最新todo
		for(Todo todo : todos) {	//将todos保存到News集合中
			new1 = new News(todo.getId(),"todo",todo.getWhoCreate(),todo.getContent(),todo.getCreateTime());
			news.add(new1);
		}
		List<Said> saids = saidService.findNew15();//最新说说
		for(Said said : saids) {	//将saids保存到News集合中
			new1 = new News(said.getId(),"said",said.getWhoCreate(),said.getContent(),said.getCreateTime());
			news.add(new1);
		}
		List<Log> newLogs = logService.findNew15();//最新日记
		for(Log log : newLogs) {	//将newLogs保存到News集合中
			new1 = new News(log.getId(),"log",log.getWhoCreate(),log.getContent(),log.getCreateTime());
			news.add(new1);
		}
		//对news集合按时间排序
		Collections.sort(news); 
		model.addAttribute("news",news);	//最新todo
		model.addAttribute("actives",userService.find20ByIntegral());//首页需要显示的活跃用户，前20
		model.addAttribute("logs",logs);			//首页需要显示的日记信息
		return "user/me/index";
	}

	/**
	 * 初始化我关注的日记页面
	 * @param thisPage 当前页数
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/focusLog", method = RequestMethod.GET)
	public String focusLog(@RequestParam("thisPage")int thisPage,
			HttpSession session, Model model) {
		System.out.println("我是处理用户关注的日记的控制层===================当前页"+thisPage);
		int whoFocuId = ((User)session.getAttribute("user")).getId();	//关注人id
		List<Integer> ids = focusService.findMeFocus(whoFocuId);		//所有我关注的好友id
		ids.add(whoFocuId);		//将我的id保存进去
		List<Log> lgs = new ArrayList<Log>();	//保存关注人的所有日记信息
		for(Integer id : ids) {
			User focused = userService.findOneById(id);
			lgs.addAll(logService.findAllByWhocreate(focused.getNickName()));
		}
		Collections.sort(lgs, new Comparator<Log>(){  
			public int compare(Log l1, Log l2) {  
				//按照日记的id进行降序排列  
				if(l1.getId() > l2.getId()){  
					return -1;  
				}  
				if(l1.getId() == l2.getId()){  
					return 0;  
				}  
				return 1;  
			}  
		}); 
		List<Logs> logs = new ArrayList<Logs>();
		//总页数
		int pageSize = lgs.size()%pageNumber != 0 ? (lgs.size()/pageNumber + 1) : (lgs.size()/pageNumber);
		System.out.println("页数大小是："+pageSize);
		//最后一页的索引为lgs集合的大小
		int toIndex;
		if(pageSize == 0) {	//页数大小为0，
			toIndex = 0;
		} else {			//页数大小不为0
			toIndex = pageSize == thisPage ? lgs.size() : thisPage*pageNumber;
		}
		//需要显示的log所有信息
		for(Log lg : lgs.subList((thisPage-1)*pageNumber, toIndex)) {
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyService.findAllNumberBylogId(lg.getId()),
					praiseLogService.findNumberByLogId(lg.getId()));
			log.setUserPhoto(userService.findImageByNickname(lg.getWhoCreate()));
			logs.add(log);
		}
		model.addAttribute("logs",logs);
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数
		return "user/friends/focusLog";
	}

	/**
	 * 初始化我的主页页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/myself", method = RequestMethod.GET)
	public String initMyself(Model model, HttpSession session) {
		System.out.println("我是初始化myself的控制层=============================");
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		List<Blog> blgs = blogService.findBywhoCreate(whoCreate);//根据用户名查找出的日记本
		Log log = new Log();
		log.setWhoCreate(whoCreate);
		List<Blogs> blogs = new ArrayList<Blogs>();	//创建一个List集合，保存前台显示的日记信息
		for(Blog blg : blgs) {
			log.setBlogName(blg.getName());
			Blogs blog = new Blogs(blg.getId(),blg.getWhoCreate(),blg.getName(),logService.findSizeByBlogName(log));
			blogs.add(blog);
		}
		Integer whoFocuId = ((User)session.getAttribute("user")).getId();//我的id
		List<Integer> myFocusIds = focusService.findMeFocus(whoFocuId);//我关注的人，
		Integer myFocusNumber = myFocusIds.size();	//我关注的好友数量
		if(myFocusIds.size() > 20) {	//前20个
			myFocusIds = myFocusIds.subList(0, 20);
		}
		List<Focuses> myFocuses = new ArrayList<Focuses>();//我关注的好友
		for(Integer myFocusId : myFocusIds) {
			User myFocusFriends = userService.findOneById(myFocusId);
			Focuses focus = new Focuses(myFocusFriends.getNickName(),myFocusFriends.getImage(),myFocusFriends.getIntegral());
			myFocuses.add(focus);
		}
		List<Integer> focusMeIds = focusService.findFocusMe(whoFocuId);//关注我的人，前20个
		Integer focusMeNumber = focusMeIds.size();//关注我的好友数量
		if(focusMeIds.size() > 20) {
			focusMeIds = focusMeIds.subList(0, 20);
		}
		List<Focuses> focusMes = new ArrayList<Focuses>();//关注我的好友
		for(Integer focusMeId : focusMeIds) {
			User focusMeFriends = userService.findOneById(focusMeId);
			Focuses focus = new Focuses(focusMeFriends.getNickName(),focusMeFriends.getImage(),focusMeFriends.getIntegral());
			focusMes.add(focus);
		}
		//活跃用户，前20个

		model.addAttribute("myFocuses",myFocuses);	//我关注的好友
		model.addAttribute("myFocusNumber",myFocusNumber);//我关注的好友数量
		model.addAttribute("focusMes",focusMes);	//关注我的好友
		model.addAttribute("focusMeNumber",focusMeNumber);//关注我的好友数量
		model.addAttribute("blogs", blogs);			//我的日记本信息
		model.addAttribute("logCount", logService.findAllNumberBywhoCreate(whoCreate));	//日记总条数
		model.addAttribute("saidCount", saidService.findAllNumberBywhoCreate(whoCreate));//说说总条数
		return "user/me/myself";
	}

	/**
	 * 初始化好友主页页面
	 * @param whoCreate	好友昵称
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/friendsIndex", method = RequestMethod.GET)
	public String initMyself(@RequestParam("whoCreate")String whoCreate ,
			Model model,
			HttpSession session) {
		System.out.println("我是初始化friendsIndex的控制层=============================");
		List<Blog> blgs = blogService.findBywhoCreate(whoCreate);//根据用户名查找出的日记本
		User friends = userService.findOneByNickname(whoCreate);//该用户的详细信息
		Log log = new Log();
		log.setWhoCreate(whoCreate);
		List<Blogs> blogs = new ArrayList<Blogs>();	//创建一个List集合，保存前台显示的日记信息
		for(Blog blg : blgs) {
			log.setBlogName(blg.getName());
			Blogs blog = new Blogs(blg.getId(),blg.getWhoCreate(),blg.getName(),logService.findSizeByBlogNameAndNovisible(log));
			blogs.add(blog);
		}
		Focus focus = new Focus(((User)session.getAttribute("user")).getId(),friends.getId());
		boolean focusInfo = focusService.checkFocus(focus) == 1 ? true : false;

		Integer whoFocuId = userService.findOneByNickname(whoCreate).getId();//我的id
		List<Integer> myFocusIds = focusService.findMeFocus(whoFocuId);//我关注的人，
		Integer myFocusNumber = myFocusIds.size();	//我关注的好友数量
		if(myFocusIds.size() > 20) {	//前20个
			myFocusIds = myFocusIds.subList(0, 20);
		}
		List<Focuses> myFocuses = new ArrayList<Focuses>();//我关注的好友
		for(Integer myFocusId : myFocusIds) {
			User myFocusFriends = userService.findOneById(myFocusId);
			Focuses fcs = new Focuses(myFocusFriends.getNickName(),myFocusFriends.getImage(),myFocusFriends.getIntegral());
			myFocuses.add(fcs);
		}
		List<Integer> focusMeIds = focusService.findFocusMe(whoFocuId);//关注我的人，前20个
		Integer focusMeNumber = focusMeIds.size();//关注我的好友数量
		if(focusMeIds.size() > 20) {
			focusMeIds = focusMeIds.subList(0, 20);
		}
		List<Focuses> focusMes = new ArrayList<Focuses>();//关注我的好友
		for(Integer focusMeId : focusMeIds) {
			User focusMeFriends = userService.findOneById(focusMeId);
			Focuses fcs = new Focuses(focusMeFriends.getNickName(),focusMeFriends.getImage(),focusMeFriends.getIntegral());
			focusMes.add(fcs);
		}
		//活跃用户，前20个

		model.addAttribute("myFocuses",myFocuses);	//我关注的好友
		model.addAttribute("myFocusNumber",myFocusNumber);//我关注的好友数量
		model.addAttribute("focusMes",focusMes);	//关注我的好友
		model.addAttribute("focusMeNumber",focusMeNumber);//关注我的好友数量

		model.addAttribute("focusInfo", focusInfo);	//关注信息true：已关注 、false：未关注
		model.addAttribute("friends", friends);
		model.addAttribute("blogs", blogs);
		model.addAttribute("logCount", logService.findNumberAndNovisibleByWhocreate(whoCreate));	//日记总条数
		model.addAttribute("saidCount", saidService.findAllNumberBywhoCreate(whoCreate));	//说说总条数
		return "user/friends/friendsIndex";
	}

	/**
	 * 初始化个人账号页面
	 * @param user	返回页面的user对象
	 * @return
	 */
	@RequestMapping(value = "user/me/account", method = RequestMethod.GET)
	public String initAccount(User user) {
		System.out.println("我是初始化account的控制层=============================");

		return "user/me/account";
	}

	/**
	 * 初始化写日记页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/addlog", method = RequestMethod.GET)
	public String addLog(Model model, HttpSession session) {
		System.out.println("我是初始化addlog的控制层=============================");
		String whoCreate = ((User)session.getAttribute("user")).getNickName();

		model.addAttribute("blogs", blogService.findBywhoCreate(whoCreate));
		return "user/blog/addlog";
	}

	/**
	 * 初始化发现页面
	 * @return
	 */
	@RequestMapping(value = "user/me/find", method = RequestMethod.GET)
	public String find() {
		System.out.println("我是初始化find的控制层=====================");

		return "user/friends/find";
	}

	/**
	 * 初始化日记详情页面
	 * @param logId	日记的id
	 * @param whoCreate 日记的创建人
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/logInfo", method = RequestMethod.GET)
	public String logInfo(@RequestParam("id")int logId,
			@RequestParam("whoCreate")String whoCreate,
			Model model) {
		System.out.println("显示log详细详细：id="+logId+"，所属人："+whoCreate);
		User author = userService.findOneByNickname(whoCreate);	//作者的详细信息

		int replyNumber = replyService.findAllNumberBylogId(logId);	//回复的数量

		Log log = logService.findOneById(logId);			//查找该日记的详细信息
		int praiseNumber = praiseLogService.findNumberByLogId(log.getId());

		List<Replys> replys = new ArrayList<Replys>();
		List<Reply> rplys = replyService.findBylogId(logId);	//全部回复信息

		for(Reply rply : rplys) {
			User use = userService.findOneById(rply.getReplyerId());
			Replys reply = new Replys(rply.getId(),use.getImage(),use.getNickName(),rply.getReplyTime(),
					use.getSignature(),rply.getContent(),use.getIntegral());
			if(rply.getReplyer2Id() != null) {//存在再次回复的信息
				User use2 = userService.findOneById(rply.getReplyer2Id());
				reply.setReplyer2Nickname(use2.getNickName());
			}
			replys.add(reply);
		}

		model.addAttribute("praiseNumber", praiseNumber);	//点赞数量
		model.addAttribute("author", author);	//作者详细信息
		model.addAttribute("replys", replys);	//回复信息
		model.addAttribute("replyNumber", replyNumber);	//回复数量
		model.addAttribute("log", log);	//日记详细信息
		return "user/blog/logInfo";
	}

	/**
	 * 初始化日记本下所有日记页面
	 * @param thisPage	当前页数
	 * @param blogName	日记本名
	 * @param whoCreate	日记本创建人
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "user/me/blogInfo", method = RequestMethod.GET)
	public String blogInfo(@RequestParam("thisPage")int thisPage,
			@RequestParam("blogName")String blogName,
			@RequestParam("whoCreate")String whoCreate,
			Model model,
			HttpSession session) {
		System.out.println("我是初始化日记本所有日记的控制层===日记本名："+blogName+",创建人："+whoCreate);
		Log lg0 = new Log();
		lg0.setBlogName(blogName);
		lg0.setWhoCreate(whoCreate);
		int count = logService.findSizeByBlogNameAndNovisible(lg0);//总条数
		if (whoCreate.equals(((User)session.getAttribute("user")).getNickName())) {
			count = logService.findSizeByBlogName(lg0);//当前查看人是本人，查看所有日记
		} else {
			count = logService.findSizeByBlogNameAndNovisible(lg0);//不是本人，查看公开日记
		}
		System.out.println("查找出来的总条数："+count);
		int pageSize = count%pageNumber != 0 ? (count/pageNumber + 1) : (count/pageNumber);//总页数

		PageForLog page = new PageForLog(whoCreate, blogName, (thisPage-1)*pageNumber, pageNumber);

		List<Log> allLog;
		if (whoCreate.equals(((User)session.getAttribute("user")).getNickName())) {
			allLog = logService.findByBlog(page);	//当前查看人是本人，查看所有日记
		} else {
			allLog = logService.findByBlogAndNovisible(page);//不是本人，查看公开日记
		}
		System.out.println("当前页的总条数："+allLog.size());
		String userPhoto = userService.findImageByNickname(whoCreate);
		int replyNumber = 0;
		List<Logs> logs = new ArrayList<Logs>();
		for(Log lg : allLog) {
			replyNumber = replyService.findAllNumberBylogId(lg.getId());
			Logs log = new Logs(lg.getId(), lg.getWhoCreate(), lg.getCreateTime(), lg.getTitle(),
					lg.getBlogName(), lg.getMood(), lg.getWeather(), lg.getPlace(), lg.getContent(),
					lg.getVisible(), lg.getReply(), replyNumber,praiseLogService.findNumberByLogId(lg.getId()));
			log.setUserPhoto(userPhoto);
			logs.add(log);
		}

		model.addAttribute("whoCreate", whoCreate);	//日记创建人
		model.addAttribute("blogName", blogName);	//该日记本名
		model.addAttribute("logs", logs);	//该页记录信息
		model.addAttribute("thisPage", thisPage);	//当前页
		model.addAttribute("pageSize", pageSize);	//总页数
		return "user/blog/blogInfo";
	}

	/**
	 * 初始化管理日记本页面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/me/manageBlog", method = RequestMethod.GET)
	public String manageBlog(HttpSession session,
			Model model) {
		String whoCreate = ((User)session.getAttribute("user")).getNickName();
		List<Blog> blogs = blogService.findBywhoCreate(whoCreate);//根据用户名查找出的日记本

		model.addAttribute("blogs", blogs);
		return "user/blog/manageBlog";
	}


	//------------------------------------后台页面初始化==================================================
	
	/**
	 * 初始化admin页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/admin", method = RequestMethod.GET)
	public String initAdmin(Model model) {
		System.out.println("我是处理管理员首页的控制层=============================");
		//	TODO 初始化管理员首页

		return "admin/admin";
	}

	
	/**
	 * 初始化管理员菜单栏点击页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/manageUser", method = RequestMethod.GET)
	public String initTemp(Model model) {
		System.out.println("我是初始化temp的控制层=============================");
		//	TODO 初始化管理员页面

		return "admin/manageUser";
	}
}
