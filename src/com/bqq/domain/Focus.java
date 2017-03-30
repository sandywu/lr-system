package com.bqq.domain;

import java.io.Serializable;

public class Focus implements Serializable{
	
	private Integer id;			//关注信息的唯一标识符
	private Integer whoFocuId;	//关注人的id
	private Integer focusWhoId;	//被关注人的id
	
	public Focus(){}
	
	public Focus(Integer whoFocuId, Integer focusWhoId) {
		super();
		this.whoFocuId = whoFocuId;
		this.focusWhoId = focusWhoId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWhoFocuId() {
		return whoFocuId;
	}
	public void setWhoFocuId(Integer whoFocuId) {
		this.whoFocuId = whoFocuId;
	}
	public Integer getFocusWhoId() {
		return focusWhoId;
	}
	public void setFocusWhoId(Integer focusWhoId) {
		this.focusWhoId = focusWhoId;
	}

}
