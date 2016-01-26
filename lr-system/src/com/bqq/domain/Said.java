package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Said implements Serializable {

	// Fields

	private Integer id;			//说说信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private Date createTime;	//创建日期
	private String content;  	//说说内容

	// Constructors

	/** default constructor */
	public Said() {
	}

	/** full constructor */
	public Said(String whoCreate, Date createTime, String content) {
		super();
		this.whoCreate = whoCreate;
		this.createTime = createTime;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		Said other = (Said) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
