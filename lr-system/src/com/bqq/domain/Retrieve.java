package com.bqq.domain;

import java.io.Serializable;

public class Retrieve implements Serializable{
	private Integer id;		//�һ������Ψһ��ʶ��
	private String email;	//�˺�����
	private String question;//����
	private String answer;	//��
	
	public Retrieve(){}
	
	public Retrieve(String email, String question, String answer) {
		super();
		this.email = email;
		this.question = question;
		this.answer = answer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
