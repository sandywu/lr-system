package com.bqq.domain;

import java.io.Serializable;

public class PraiseSaid implements Serializable{
	
	private Integer id;
	private Integer whoPraiseId;	//点赞人的id
	private Integer praiseSaidId;	//点赞说说的id
	
	public PraiseSaid(){}
	
	public PraiseSaid(Integer whoPraiseId, Integer praiseSaidId) {
		super();
		this.whoPraiseId = whoPraiseId;
		this.praiseSaidId = praiseSaidId;
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
	public Integer getPraiseSaidId() {
		return praiseSaidId;
	}
	public void setPraiseSaidId(Integer praiseSaidId) {
		this.praiseSaidId = praiseSaidId;
	}
	
}
