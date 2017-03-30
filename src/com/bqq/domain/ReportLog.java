package com.bqq.domain;

import java.io.Serializable;

public class ReportLog implements Serializable {
	private Integer id;			//举报对象的唯一标识符
	private Integer logId;		//被举报日记的id
	private Integer reporterId;	//举报人的id
	private String reason;		//举报原因
	
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
