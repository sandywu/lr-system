package com.bqq.web;

public class Blogs {
	private Integer id;			//�ռǱ���Ϣ��Ψһ��ʶ��
	private String whoCreate;	//�������ǳ�
	private String name;		//�ռǱ���
	private int size;			//��ǰ�ռǱ����ռ�����
	
	
	public Blogs(Integer id, String whoCreate, String name, int size) {
		super();
		this.id = id;
		this.whoCreate = whoCreate;
		this.name = name;
		this.size = size;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWhoCreate() {
		return whoCreate;
	}
	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	

}
