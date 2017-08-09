package com.spzwl.admin.custromer.dao.excption;

public class NoFileUploadException extends Exception {

	public NoFileUploadException() {
		super();
	}

	public NoFileUploadException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoFileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFileUploadException(String message) {
		super(message);
	}

	public NoFileUploadException(Throwable cause) {
		super(cause);
	}
	

}
