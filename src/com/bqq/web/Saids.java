package com.bqq.web;

import java.util.Date;

public class Saids {
	private Integer id;			//˵˵��Ϣ��Ψһ��ʶ��
	private String whoCreate;	//�������ǳ�
	private Date createTime;	//��������
	private String content;  	//˵˵����
	private String userPhoto;	//�û�ͷ��
	private Integer praiseNumber;//��������
	
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
