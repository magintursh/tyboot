package org.typroject.tyboot.core.auth.enumeration;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AuthType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AuthType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum AuthType implements ProvidedAuthType{
	ID_PASSWORD("idPasswordLoginAuthenticator");




	 AuthType(String authenticator)
	{
			this.authenticator = authenticator;
	}

	private String  authenticator;


	public String getAuthenticator() {
		return authenticator;
	}

	public String getAuthType()
	{
		return this.name();
	}

}

/*
 * $Log: lexingbuild.bat,v $
 */