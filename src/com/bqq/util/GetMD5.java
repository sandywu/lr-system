package com.bqq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetMD5 {
	/**
	 * 工具类构造方法私有
	 */
	private GetMD5(){}
	
	/** 
     * 生成MD5简单方法 
     * @param str 需要生成的字符串
     * @return 	生成后的字符串
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
        	System.out.println("MD5异常。。。。。。");
            e.printStackTrace();  
        }  
        return sb.toString();  
    } 

}
