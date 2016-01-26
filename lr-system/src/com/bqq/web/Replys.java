package com.bqq.web;

import java.util.Date;

public class Replys {
	private Integer replyId;
	private String replyerPhoto;
	private String replyerNickname;
	private Date replyDate;
	private String replyerSignature;
	private String replyContent;
	private Integer integral;
	private String replyer2Nickname;	//再次回复的回复人id
	
	
	public Replys(Integer replyId, String replyerPhoto, String replyerNickname, Date replyDate,
			String replyerSignature, String replyContent, Integer integral) {
		super();
		this.replyId = replyId;
		this.replyerPhoto = replyerPhoto;
		this.replyerNickname = replyerNickname;
		this.replyDate = replyDate;
		this.replyerSignature = replyerSignature;
		this.replyContent = replyContent;
	}
	
	public String getReplyer2Nickname() {
		return replyer2Nickname;
	}

	public void setReplyer2Nickname(String replyer2Nickname) {
		this.replyer2Nickname = replyer2Nickname;
	}

	public Integer getReplyId() {
		return replyId;
	}


	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}


	public Integer getIntegral() {
		return integral;
	}


	public void setIntegral(Integer integral) {
		this.integral = integral;
	}


	public String getReplyerPhoto() {
		return replyerPhoto;
	}
	public void setReplyerPhoto(String replyerPhoto) {
		this.replyerPhoto = replyerPhoto;
	}
	public String getReplyerNickname() {
		return replyerNickname;
	}
	public void setReplyerNickname(String replyerNickname) {
		this.replyerNickname = replyerNickname;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyerSignature() {
		return replyerSignature;
	}
	public void setReplyerSignature(String replyerSignature) {
		this.replyerSignature = replyerSignature;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
	
}
