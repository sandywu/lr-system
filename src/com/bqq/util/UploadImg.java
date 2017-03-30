package com.bqq.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bqq.exception.ImageUploadException;

public class UploadImg {
	
	/**
	 * �����๹�췽��Ϊ˽��
	 */
	private UploadImg() {}
	
	/**
	 * ����ϴ�ͼƬ��ʽ�Ƿ���ȷ
	 * @param image
	 */
	public static void validateImage(MultipartFile image) {
		if(!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("ֻ����JPG��ʽ��ͼƬ�� ==��==��==��==��==��==��==��");
		}
	}
	
	/**
	 * �����ϴ���ͼƬ
	 * @param fileName	ͼƬ��
	 * @param image	MultipartFile���͵�ͼƬ��Ϣ
	 * @param req	HttpServletRequest����
	 * @throws ImageUploadException	�Զ����쳣
	 */
	public static void saveImg(String fileName, MultipartFile image, HttpServletRequest req) throws ImageUploadException {
		try {
			System.out.println("ϵͳ��ȡ�ĸ�·����" + req.getContextPath() + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			File file = new File(req.getContextPath() + "/resources/" + fileName);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch(IOException e) {
			throw new ImageUploadException("�޷������ϴ���ͼƬ ==��==��==��==��==��==��==��", e);
		}
	}
}
