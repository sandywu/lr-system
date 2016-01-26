package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

	// Fields

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

	@Override
	public String toString() {
		return "Log [id=" + id + ", whoCreate=" + whoCreate + ", createTime=" + createTime + ", title=" + title
				+ ", blogName=" + blogName + ", mood=" + mood + ", weather=" + weather + ", place=" + place
				+ ", content=" + content + ", visible=" + visible + ", reply=" + reply + "]";
	}

	// Constructors
	/** default constructor */
	public Log() {
	}

	public Log(String whoCreate, Date createTime, String title,
			String blogName, String mood, String weather, String place,
			String content) {
		super();
		this.whoCreate = whoCreate;
		this.createTime = createTime;
		this.title = title;
		this.blogName = blogName;
		this.mood = mood;
		this.weather = weather;
		this.place = place;
		this.content = content;
	}

	/** minimal constructor */
	public Log(Integer id, String whoCreate, Date createTime, String title,
			String blogName, String content, Boolean visible, Boolean reply) {
		super();
		this.id = id;
		this.whoCreate = whoCreate;
		this.createTime = createTime;
		this.title = title;
		this.blogName = blogName;
		this.content = content;
		this.visible = visible;
		this.reply = reply;
	}

	/** full constructor */
	public Log(Integer id, String whoCreate, Date createTime, String title,
			String blogName, String mood, String weather, String place,
			String content, Boolean visible, Boolean reply) {
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
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
