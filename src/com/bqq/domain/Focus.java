package com.bqq.domain;

import java.io.Serializable;

public class Focus implements Serializable{
	
	private Integer id;			//��ע��Ϣ��Ψһ��ʶ��
	private Integer whoFocuId;	//��ע�˵�id
	private Integer focusWhoId;	//����ע�˵�id
	
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
