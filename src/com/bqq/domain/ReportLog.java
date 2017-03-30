package com.bqq.domain;

import java.io.Serializable;

public class ReportLog implements Serializable {
	private Integer id;			//�ٱ������Ψһ��ʶ��
	private Integer logId;		//���ٱ��ռǵ�id
	private Integer reporterId;	//�ٱ��˵�id
	private String reason;		//�ٱ�ԭ��
	
	public ReportLog(){}
	
	public ReportLog(Integer logId, Integer reporterId, String reason) {
		super();
		this.logId = logId;
		this.reporterId = reporterId;
		this.reason = reason;
	}
	
	public ReportLog(Integer logId, Integer reporterId) {
		super();
		this.logId = logId;
		this.reporterId = reporterId;
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
	public Integer getReporterId() {
		return reporterId;
	}
	public void setReporterId(Integer reporterId) {
		this.reporterId = reporterId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
