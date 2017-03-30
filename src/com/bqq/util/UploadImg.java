package com.bqq.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bqq.exception.ImageUploadException;

public class UploadImg {
	
	/**
	 * 工具类构造方法为私有
	 */
	private UploadImg() {}
	
	/**
	 * 检测上传图片格式是否正确
	 * @param image
	 */
	public static void validateImage(MultipartFile image) {
		if(!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("只接受JPG格式的图片！ ==！==！==！==！==！==！==！");
		}
	}
	
	/**
	 * 保存上传的图片
	 * @param fileName	图片名
	 * @param image	MultipartFile类型的图片信息
	 * @param req	HttpServletRequest对象
	 * @throws ImageUploadException	自定义异常
	 */
	public static void saveImg(String fileName, MultipartFile image, HttpServletRequest req) throws ImageUploadException {
		try {
			System.out.println("系统获取的根路径：" + req.getContextPath() + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			File file = new File(req.getContextPath() + "/resources/" + fileName);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch(IOException e) {
			throw new ImageUploadException("无法保存上传的图片 ==！==！==！==！==！==！==！", e);
		}
	}
}
