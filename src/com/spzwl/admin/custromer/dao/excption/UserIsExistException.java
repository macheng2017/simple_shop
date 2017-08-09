package com.spzwl.admin.custromer.dao.excption;


public class UserIsExistException extends Exception {

	public UserIsExistException() {
		super();
	}

	public UserIsExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserIsExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsExistException(String message) {
		super(message);
	}

	public UserIsExistException(Throwable cause) {
		super(cause);
	}



}
