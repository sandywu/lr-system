package com.bqq.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bqq.domain.StmMassage;
import com.bqq.domain.User;
import com.bqq.service.StmMassageService;

@Controller
public class StmMassageController {
	@Autowired
	StmMassageService stmMassageService;
	
	@RequestMapping(value = "admin/addStmMsg", method = RequestMethod.POST)
	public String addStmMsg(@RequestParam("title")String title,@RequestParam("content")String content,
			HttpSession session,Model model) {
		StmMassage stmMassage = new StmMassage();
		stmMassage.setAdminId(((User)session.getAttribute("user")).getId());
		stmMassage.setTitle(title);
		stmMassage.setContent(content);
		stmMassage.setStartTime(new Date());
		stmMassage.setState(1);
		System.out.println(stmMassage);
		stmMassageService.addStmMsg(stmMassage);
		return "redirect:manageStmMsg?thisPage=1";
	}
	
	@RequestMapping(value = "admin/updateState", method = RequestMethod.GET)
	public String updateState(@RequestParam("id")int id,@RequestParam("toState")int toState,
			@RequestParam("thisPage")int thisPage) {
		StmMassage stmMassage = new StmMassage();
		stmMassage.setId(id);
		stmMassage.setState(toState);
		stmMassage.setEndTime(new Date());
		stmMassageService.updateState(stmMassage);
		
		return "redirect:manageStmMsg?thisPage="+thisPage;
	}
	

}
