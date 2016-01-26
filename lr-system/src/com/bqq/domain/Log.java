package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

	// Fields

	private Integer id;			//日记信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private Date createTime;	//创建日期
	private String title;		//日记标题
	private String blogName;	//所属日记本名
	private String mood;		//心情
	private String weather;		//天气
	private String place;		//地点
	private String content;		//日记内容
	private Boolean visible;	//其他人是否可见 true：可见、false：不可见
	private Boolean reply;		//是否可回复 true：可回复、false：不可回复

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
