package org.typroject.tyboot.core.restful.utils;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: APILevel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: APILevel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public enum APILevel
{
	ALL(0),  //通用级别
	
	PUBLIC(100),//为第三方开放的接口

	CUSTOMER(200),//本地用户级别

	AGENCY(300),//商户级别

	SUPERADMIN(400);//超级管理员级别


	private int value;

	 APILevel(int str)
	{
		this.value = str;
	}


	public int getValue()
	{
		return value;
	}
}

/*
 * $Log: av-env.bat,v $
 */