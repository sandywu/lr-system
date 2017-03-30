package com.bqq.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqq.domain.ReportLog;
import com.bqq.domain.User;
import com.bqq.service.ReportLogService;

@Controller("ReportLogController")
public class ReportLogController {
	@Autowired
	ReportLogService reportLogService;
	
	@RequestMapping(value = "user/me/checkReport", method = RequestMethod.GET)
	public @ResponseBody int checkReport(@RequestParam("logId")int logId,
			HttpSession session) {
		int reporterId = ((User)session.getAttribute("user")).getId();
		ReportLog reportLog = new ReportLog(logId,reporterId);
		return reportLogService.checkReport(reportLog);
	}
	
	@RequestMapping(value = "user/me/addReport", method = RequestMethod.GET)
	public @ResponseBody int addReport(@RequestParam("logId")int logId,
			@RequestParam("reason")String reason,
			HttpSession session) {
		int reporterId = ((User)session.getAttribute("user")).getId();
		ReportLog reportLog = new ReportLog(logId,reporterId,reason);
		return reportLogService.addReport(reportLog);
	}

}
