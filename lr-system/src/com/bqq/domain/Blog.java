package com.bqq.domain;

import java.io.Serializable;

public class Blog implements Serializable {
	
	// Fields

	private Integer id;			//日记本信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private String name;		//日记本名

	// Constructors

	/** default constructor */
	public Blog() {
	}

	/** full constructor */
	public Blog(String whoCreate, String name) {
		super();
		this.whoCreate = whoCreate;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Blog other = (Blog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
