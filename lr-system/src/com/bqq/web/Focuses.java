package com.bqq.web;

public class Focuses {

	private Integer id;			//��ע��Ϣ��Ψһ��ʶ��
	private Integer whoFocuId;	//��ע�˵�id
	private Integer focusWhoId;	//����ע�˵�id
	private String whoFocuNickname;	//��ע�˵��ǳ�
	private String whoFocuPhoto;	//��ע�˵�ͷ��
	private Integer whoFocuIntegral;	//��ע�˵Ļ���
	
	
	
	public Focuses(Integer whoFocuId, Integer focusWhoId, String whoFocuNickname, String whoFocuPhoto,
			Integer whoFocuIntegral) {
		super();
		this.whoFocuId = whoFocuId;
		this.focusWhoId = focusWhoId;
		this.whoFocuNickname = whoFocuNickname;
		this.whoFocuPhoto = whoFocuPhoto;
		this.whoFocuIntegral = whoFocuIntegral;
	}
	public Focuses( String whoFocuNickname, String whoFocuPhoto, Integer whoFocuIntegral) {
		super();
		this.whoFocuNickname = whoFocuNickname;
		this.whoFocuPhoto = whoFocuPhoto;
		this.whoFocuIntegral = whoFocuIntegral;
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
	public String getWhoFocuNickname() {
		return whoFocuNickname;
	}
	public void setWhoFocuNickname(String whoFocuNickname) {
		this.whoFocuNickname = whoFocuNickname;
	}
	public String getWhoFocuPhoto() {
		return whoFocuPhoto;
	}
	public void setWhoFocuPhoto(String whoFocuPhoto) {
		this.whoFocuPhoto = whoFocuPhoto;
	}
	public Integer getWhoFocuIntegral() {
		return whoFocuIntegral;
	}
	public void setWhoFocuIntegral(Integer whoFocuIntegral) {
		this.whoFocuIntegral = whoFocuIntegral;
	}
	
	
}
