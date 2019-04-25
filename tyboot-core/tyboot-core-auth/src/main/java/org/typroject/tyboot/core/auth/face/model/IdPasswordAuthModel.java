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
public class IdPasswordAuthModel extends AuthModel {
	private static final long serialVersionUID = -5347656325432534L;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

/*
 * $Log: av-env.bat,v $
 */