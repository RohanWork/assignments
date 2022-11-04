package com.task.rohan.exception;

public class CustomException extends RuntimeException{

	private int code;
	private String message;
	
	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getcode() {
		return code;
	}

	public void setcode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
