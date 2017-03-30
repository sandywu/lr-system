package com.bqq.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class ImageUploadException extends RuntimeException {
	
	/**
	 * У��ͼƬ��ʽʱ�õ�
	 * @param message	��ʾ��Ϣ
	 */
	public ImageUploadException(String message) {
		super(message);
	}

	/**
	 * ����ͼƬʱ�õ�
	 * @param message	��ʾ��Ϣ
	 * @param e	IO�쳣
	 */
	public ImageUploadException(String message, IOException e) {
		super(message, e);
	}
}
