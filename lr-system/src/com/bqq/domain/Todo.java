package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
	
	// Fields

	private Integer id;			//计划信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private String content;		//计划的内容信息
	private Integer priority;	//计划的优先级
	private Date createTime;	//创建日期
	private Boolean isComplete;	//是否完成 true：已完成、false：未完成

	// Constructors

	/** default constructor */
	public Todo() {
	}

	/** full constructor */
	public Todo(String whoCreate, String content, Integer priority,
			Date createTime, Boolean isComplete) {
		super();
		this.whoCreate = whoCreate;
		this.content = content;
		this.priority = priority;
		this.createTime = createTime;
		this.isComplete = isComplete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getWhoCreate() {
		return whoCreate;
	}

	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
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
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
