package com.bqq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bqq.domain.User;

public class GetContent {
	
	private GetContent(){}
	
	public static String getContent(User user) {
		//SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime = null;
		try {
			createTime = form.parse(form.format(user.getCreateTime()));
		} catch (ParseException e) {
			System.out.println("���ڸ�ʽ������======");
			e.printStackTrace();
		}
		//shanks��2016��xxx���룬ta��������[xxxx]��ta��Ȩ����[xxx]������ǩ��[xxx]�����˼��[]
		StringBuilder sb = new StringBuilder();
		sb.append(user.getNickName());
		sb.append("��");
		sb.append(createTime);
		sb.append("��������΢������վ��ta��������[");
		sb.append(user.getEmail());
		sb.append("]��ta��Ȩ����[");
		if(user.getIsAdmin()) {
			sb.append("����Ա");
		}else{
			sb.append("��ͨ�û�");
		}
		sb.append("]������ǩ��[");
		if(user.getSignature() != null) {
			sb.append(user.getSignature());
		}else{
			sb.append("����");
		}
		sb.append("]�����˼��[");
		if(user.getDescrib() != null) {
			sb.append(user.getDescrib()+"]");
		}else{
			sb.append("����]");
		}
		sb.append("����עta�˽���༴ʱ��Ϣ...������΢��������SHANKS������һ�������ռ�����վ��");
		sb.append("��������Է�������־Ȥ��Ͷ�ĺ���...");
		return sb.toString();
	}

}
