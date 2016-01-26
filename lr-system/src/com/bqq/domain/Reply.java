package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable{
	private Integer id;			//�ظ���Ϣ��Ψһ��ʶ��
	private Integer logId;		//�ظ��ռǵ�id
	private Integer replyerId;	//�ظ���id
	private Integer replyer2Id;	//���ظ��˵�id
	private String content;		//�ظ�����
	private Date replyTime;		//�ظ�����
	
	public Reply() {}
	
	public Reply(Integer logId, Integer replyerId, String content,
			Date replyTime) {
		super();
		this.logId = logId;
		this.replyerId = replyerId;
		this.content = content;
		this.replyTime = replyTime;
	}
	
	public Reply(Integer id, Integer logId, Integer replyerId, String content,
			Date replyTime) {
		super();
		this.id = id;
		this.logId = logId;
		this.replyerId = replyerId;
		this.content = content;
		this.replyTime = replyTime;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getReplyerId() {
		return replyerId;
	}
	public void setReplyerId(Integer replyerId) {
		this.replyerId = replyerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReplyer2Id() {
		return replyer2Id;
	}

	public void setReplyer2Id(Integer replyer2Id) {
		this.replyer2Id = replyer2Id;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", logId=" + logId + ", replyerId=" + replyerId + ", replyer2Id=" + replyer2Id
				+ ", content=" + content + ", replyTime=" + replyTime + "]";
	}
	
	
}
