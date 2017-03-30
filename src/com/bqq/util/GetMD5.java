package com.bqq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetMD5 {
	/**
	 * �����๹�췽��˽��
	 */
	private GetMD5(){}
	
	/** 
     * ����MD5�򵥷��� 
     * @param str ��Ҫ���ɵ��ַ���
     * @return 	���ɺ���ַ���
     */  
    public static String getMD5(String str){  
        byte [] buf=str.getBytes();  
        MessageDigest md5;  
        StringBuilder sb=new StringBuilder();  
        try {  
            md5 = MessageDigest.getInstance("MD5");  
            md5.update(buf);  
            byte [] tmp=md5.digest();  
            for (byte b:tmp) {  
                sb.append(Integer.toHexString(b&0xff));  
            }  
        } catch (NoSuchAlgorithmException e) {  
        	System.out.println("MD5�쳣������������");
            e.printStackTrace();  
        }  
        return sb.toString();  
    } 

}
