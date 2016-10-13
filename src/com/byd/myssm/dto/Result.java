package com.byd.myssm.dto;

/**
 * 封装json对象，所有返回结果都使用它
 */
public class Result<T> {

	private boolean success;	// 是否成功标志

	private T data;				// 成功时返回的数据

	private String message;		// 消息信息

	public Result() {
	}

	// 成功时的构造器
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	// 错误时的构造器
	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
		this.data = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		String result = "{\"success\":" + success ;
		result += ((success)?",\"data\":" + data.toString():"");
		result += ",\"message\":\"" + message + "\"}";
		return result;
	}

}
