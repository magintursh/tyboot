package org.typroject.tyboot.core.auth.face.model;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: IdPasswordAuthModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: IdPasswordAuthModel.java  Tyrest\magintrursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class SmsAuthModel extends AuthModel{
	private static final long serialVersionUID = -536412498052440039L;


	private String identifyingCode;//验证码


	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

}

/*
 * $Log: av-env.bat,v $
 */