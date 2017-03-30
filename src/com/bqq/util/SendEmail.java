package com.bqq.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	private static final String HOST = "smtp.163.com";//邮箱服务器
	private static final String PROTOCOL = "smtp";  //
	private static final int PORT = 25;	//端口号
	private static final String FROM = "XXXXX@163.com";//发件人的email
	private static final String PWD = "xxxxx";//TODO 发件人邮箱密码.填写自己的163邮箱密码
	
	/**
	 * 工具类的构造方法为私有
	 */
	private SendEmail(){}
	/**
	 * 获取Session
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);//设置服务器地址
		props.put("mail.store.protocol" , PROTOCOL);//设置协议
		props.put("mail.smtp.port", PORT);//设置端口
		props.put("mail.smtp.auth" , true);
		Authenticator authenticator = new Authenticator() {
			@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }
		};
		Session session = Session.getDefaultInstance(props, authenticator);
		return session;
	}
	
	public static void send(final String toEmail, final String nickName, final String password) {
		Session session = getSession();
		try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(getContent(nickName, password) , "text/html;charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException mex) {
        	System.out.println("邮件发送异常！=========================");
            mex.printStackTrace();
        }
	}
	
	private static String getContent(String nickName, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("亲爱的：");
        sb.append(nickName);
        sb.append(" 您好。     欢迎注册左右微博客网站，你的登录密码为：‘");
        sb.append(password);
        sb.append("’打死不要告诉别人！。快去登录吧！（登录后记得修改密码哦！~~ ）");
        return sb.toString();
	}

}
