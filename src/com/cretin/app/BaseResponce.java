package com.cretin.app;

/**
 * 
 * <p>
 * Title: BaseResponce
 * </p>
 * <p>
 * Description: 通用返回数据包装类
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月19日
 */
public class BaseResponce<T> {
	// 返回数据通用message
	private String message = "数据返回成功";
	// 请求状态码 0 失败 1 成功 2 登录时效
	private int code;
	// 返回数据
	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
