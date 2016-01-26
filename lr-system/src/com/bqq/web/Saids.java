package com.bqq.web;

import java.util.Date;

public class Saids {
	private Integer id;			//说说信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private Date createTime;	//创建日期
	private String content;  	//说说内容
	private String userPhoto;	//用户头像
	private Integer praiseNumber;//点赞数量
	
	public Saids(Integer id, String whoCreate, Date createTime, String content, String userPhoto,Integer praiseNumber) {
		super();
		this.id = id;
		this.whoCreate = whoCreate;
		this.createTime = createTime;
		this.content = content;
		this.userPhoto = userPhoto;
		this.praiseNumber =praiseNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWhoCreate() {
		return whoCreate;
	}
	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public Integer getPraiseNumber() {
		return praiseNumber;
	}
	public void setPraiseNumber(Integer praiseNumber) {
		this.praiseNumber = praiseNumber;
	}
	
	

}
