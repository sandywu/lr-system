package com.bqq.web;

import java.util.Date;

public class Logs {
	private Integer id;			//�ռ���Ϣ��Ψһ��ʶ��
	private String whoCreate;	//�������ǳ�
	private Date createTime;	//��������
	private String title;		//�ռǱ���
	private String blogName;	//�����ռǱ���
	private String mood;		//����
	private String weather;		//����
	private String place;		//�ص�
	private String content;		//�ռ�����
	private Boolean visible;	//�������Ƿ�ɼ� true���ɼ���false�����ɼ�
	private Boolean reply;		//�Ƿ�ɻظ� true���ɻظ���false�����ɻظ�
	private Integer replyNumber;//�ظ�����
	private String userPhoto;	//�ռ������˵�ͷ��
	private Integer praiseNumber;//��������
	
	
	public Logs(Integer id, String whoCreate, Date createTime, String title, String blogName, String mood,
			String weather, String place, String content, Boolean visible, Boolean reply, Integer replyNumber
			,Integer praiseNumber) {
		super();
		this.id = id;
		this.whoCreate = whoCreate;
		this.createTime = createTime;
		this.title = title;
		this.blogName = blogName;
		this.mood = mood;
		this.weather = weather;
		this.place = place;
		this.content = content;
		this.visible = visible;
		this.reply = reply;
		this.replyNumber = replyNumber;
		this.praiseNumber = praiseNumber;
	}

	
	public Integer getPraiseNumber() {
		return praiseNumber;
	}


	public void setPraiseNumber(Integer praiseNumber) {
		this.praiseNumber = praiseNumber;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getReply() {
		return reply;
	}

	public void setReply(Boolean reply) {
		this.reply = reply;
	}

	public Integer getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(Integer replyNumber) {
		this.replyNumber = replyNumber;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	
	
}
