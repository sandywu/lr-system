package com.bqq.util;

public class UpdateNickname {
	private String oldNickname;
	private String newNickname;
	
	public UpdateNickname(String oldNickname, String newNickname) {
		super();
		this.oldNickname = oldNickname;
		this.newNickname = newNickname;
	}
	public String getOldNickname() {
		return oldNickname;
	}
	public void setOldNickname(String oldNickname) {
		this.oldNickname = oldNickname;
	}
	public String getNewNickname() {
		return newNickname;
	}
	public void setNewNickname(String newNickname) {
		this.newNickname = newNickname;
	}
	
	

}
