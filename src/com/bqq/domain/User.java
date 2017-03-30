package com.bqq.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	// Fields

	private Integer id;			//�û���Ϣ��Ψһ��ʶ��
	private String nickName;	//�û��ǳ�
	private String password;	//�˺�����
	private String email;		//�˺�����
	private String state;		//�˺�״̬  1�����á�0��������
	private Boolean isAdmin;	//�Ƿ��ǹ���Ա true������Ա��false����ͨ�û�
	private String describ;		//��������
	private String signature;	//����ǩ��
	private Integer integral;	//�˺Ż���
	private Date createTime;	//�˺Ŵ�������
	private String image;		//�û�ͷ��
	private String qrCode;		//�û���ά������

	// Constructors

	/** default constructor */
	public User() {
	}
	
	public User(String nickName, Integer integral) {
		super();
		this.nickName = nickName;
		this.integral = integral;
	}



	/** minimal constructor */
	public User(String nickName, String password, String email, String state,
			Boolean isAdmin, Integer integral, Date createTime) {
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.state = state;
		this.isAdmin = isAdmin;
		this.integral = integral;
		this.createTime = createTime;
	}

	/** full constructor */
	public User(Integer id, String nickName, String password, String email,
			String state, Boolean isAdmin,
			String describ, String signature, Integer integral,
			Date createTime, String image) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.state = state;
		this.isAdmin = isAdmin;
		this.describ = describ;
		this.signature = signature;
		this.integral = integral;
		this.createTime = createTime;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getDescrib() {
		return describ;
	}

	public void setDescrib(String describ) {
		this.describ = describ;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", password="
				+ password + ", email=" + email + ", state=" + state
				+ ", isAdmin=" + isAdmin 
				+ ", describ=" + describ + ", signature=" + signature
				+ ", integral=" + integral + ", createTime=" + createTime + "]";
	}
	
	
}
