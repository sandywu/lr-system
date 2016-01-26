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
			System.out.println("日期格式化错误！======");
			e.printStackTrace();
		}
		//shanks于2016年xxx加入，ta的邮箱是[xxxx]，ta的权限是[xxx]，个性签名[xxx]，个人简介[]
		StringBuilder sb = new StringBuilder();
		sb.append(user.getNickName());
		sb.append("于");
		sb.append(createTime);
		sb.append("加入左右微博客网站，ta的邮箱是[");
		sb.append(user.getEmail());
		sb.append("]，ta的权限是[");
		if(user.getIsAdmin()) {
			sb.append("管理员");
		}else{
			sb.append("普通用户");
		}
		sb.append("]，个性签名[");
		if(user.getSignature() != null) {
			sb.append(user.getSignature());
		}else{
			sb.append("暂无");
		}
		sb.append("]，个人简介[");
		if(user.getDescrib() != null) {
			sb.append(user.getDescrib()+"]");
		}else{
			sb.append("暂无]");
		}
		sb.append("，关注ta了解更多即时信息...。左右微博客是由SHANKS开发的一个个人日记类网站，");
		sb.append("在这里可以发现与您志趣相投的好友...");
		return sb.toString();
	}

}
