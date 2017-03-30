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
	private static final String HOST = "smtp.163.com";//���������
	private static final String PROTOCOL = "smtp";  //
	private static final int PORT = 25;	//�˿ں�
	private static final String FROM = "XXXXX@163.com";//�����˵�email
	private static final String PWD = "xxxxx";//TODO ��������������.��д�Լ���163��������
	
	/**
	 * ������Ĺ��췽��Ϊ˽��
	 */
	private SendEmail(){}
	/**
	 * ��ȡSession
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);//���÷�������ַ
		props.put("mail.store.protocol" , PROTOCOL);//����Э��
		props.put("mail.smtp.port", PORT);//���ö˿�
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
            msg.setSubject("�˺ż����ʼ�");
            msg.setSentDate(new Date());
            msg.setContent(getContent(nickName, password) , "text/html;charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException mex) {
        	System.out.println("�ʼ������쳣��=========================");
            mex.printStackTrace();
        }
	}
	
	private static String getContent(String nickName, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("�װ��ģ�");
        sb.append(nickName);
        sb.append(" ���á�     ��ӭע������΢������վ����ĵ�¼����Ϊ����");
        sb.append(password);
        sb.append("��������Ҫ���߱��ˣ�����ȥ��¼�ɣ�����¼��ǵ��޸�����Ŷ��~~ ��");
        return sb.toString();
	}

}
