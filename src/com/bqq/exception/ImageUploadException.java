package com.bqq.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class ImageUploadException extends RuntimeException {
	
	/**
	 * 校验图片格式时用到
	 * @param message	提示消息
	 */
	public ImageUploadException(String message) {
		super(message);
	}

	/**
	 * 保存图片时用到
	 * @param message	提示消息
	 * @param e	IO异常
	 */
	public ImageUploadException(String message, IOException e) {
		super(message, e);
	}
}
