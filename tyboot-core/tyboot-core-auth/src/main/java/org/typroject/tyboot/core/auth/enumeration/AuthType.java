package org.typroject.tyboot.core.auth.enumeration;


import org.typroject.tyboot.core.auth.authentication.IdPasswordLoginAuthenticator;
import org.typroject.tyboot.core.auth.authentication.LoginAuthenticatorHandler;

public enum AuthType implements ProvidedAuthType{
	ID_PASSWORD(IdPasswordLoginAuthenticator.class);




	 AuthType(Class<? extends LoginAuthenticatorHandler> authenticator)
	{
			this.authenticator = authenticator;
	}

	private Class<? extends LoginAuthenticatorHandler>  authenticator;


	public Class<? extends LoginAuthenticatorHandler> getAuthenticator() {
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