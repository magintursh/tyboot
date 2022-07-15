package org.typroject.tyboot.core.restful.utils;

import java.io.Serializable;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ResponseModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ResponseModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ResponseModel<T> implements Serializable
{
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = -1241360949457314497L;

	/**
	 * 请求处理结果状态，会填充http状态码 正常为200，401 为token失效
	 */
	private int status;

	/**
	 * 接口结果数据
	 */
	private T result ;

	/**
	 * 请求标识
	 */
	private String traceId;

	/**
	 * 研发调试用的信息字段，系统报错的时候 错误信息会打印在这个字段
	 */
	private String devMessage = "";

	/**
	 * 
	 */
	private String message = "";

	private String path;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDevMessage() {
		return devMessage;
	}

	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}

	public String getTraceId()
	{
		return traceId;
	}

	public void setTraceId(String traceId)
	{
		this.traceId = traceId;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public T getResult()
	{
		return result;
	}

	public void setResult(T result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "ResponseModel [status=" + status + ", result=" + result + ", traceId=" + traceId + ", message="
				+ devMessage + "]";
	}
}

/*
*$Log: av-env.bat,v $
*/