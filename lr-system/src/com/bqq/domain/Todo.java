package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
	
	// Fields

	private Integer id;			//�ƻ���Ϣ��Ψһ��ʶ��
	private String whoCreate;	//�������ǳ�
	private String content;		//�ƻ���������Ϣ
	private Integer priority;	//�ƻ������ȼ�
	private Date createTime;	//��������
	private Boolean isComplete;	//�Ƿ���� true������ɡ�false��δ���

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
