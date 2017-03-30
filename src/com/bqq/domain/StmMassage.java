package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class StmMassage implements Serializable{
	private int id;		//唯一标识符
	private int adminId;//发布者id
	private String title;//标题
	private Date startTime;//开始发布时间
	private Date endTime;//结束发布时间
	private String content;//内容
	private int state;	//状态 1：正在发布中，0：已取消发布
	
	
	
	@Override
	public String toString() {
		return "StmMassage [id=" + id + ", adminId=" + adminId + ", title=" + title + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", content=" + content + ", state=" + state + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	
}
