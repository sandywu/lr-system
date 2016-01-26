package com.bqq.domain;

import java.io.Serializable;

public class PraiseLog implements Serializable{
	
	private Integer id;
	private Integer whoPraiseId;	//点赞人的id
	private Integer praiseLogId;	//点赞日记的id
	
	public PraiseLog(){}
	
	public PraiseLog(Integer whoPraiseId, Integer praiseLogId) {
		super();
		this.whoPraiseId = whoPraiseId;
		this.praiseLogId = praiseLogId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWhoPraiseId() {
		return whoPraiseId;
	}

	public void setWhoPraiseId(Integer whoPraiseId) {
		this.whoPraiseId = whoPraiseId;
	}

	public Integer getPraiseLogId() {
		return praiseLogId;
	}

	public void setPraiseLogId(Integer praiseLogId) {
		this.praiseLogId = praiseLogId;
	}
	
	

}
