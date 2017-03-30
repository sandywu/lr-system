package com.bqq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqq.domain.Retrieve;
import com.bqq.service.RetrieveService;

@Controller("RetrieveController")
public class RetrieveController {
	
	@Autowired
	RetrieveService retrieveService;
	
	@RequestMapping(value = "getQuestion", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkEmail(@RequestParam("email") String email) {
		System.out.println("我是处理ajax检测邮箱下问题的控制层=============================");
		System.out.println("问题是："+retrieveService.findQuestionByEmail(email));
		
		return retrieveService.findQuestionByEmail(email);
	}
	
	@RequestMapping(value = "checkAnswer", method = RequestMethod.GET)
	public @ResponseBody int checkAnswer(@RequestParam("answer") String answer,
			@RequestParam("email") String email) {
		System.out.println("我是处理ajax检测问题答案正确的控制层=============================");
		Retrieve retrieve = new Retrieve();
		retrieve.setEmail(email);
		retrieve.setAnswer(answer);
		
		return retrieveService.checkAnswerByEmail(retrieve);
	}
	

}
