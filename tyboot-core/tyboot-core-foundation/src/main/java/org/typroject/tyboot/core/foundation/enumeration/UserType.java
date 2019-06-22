package org.typroject.tyboot.core.foundation.enumeration;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: UserType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:枚举对应value对应APILevel
 *  TODO
 * 
 *  Notes:
 *  $Id: UserType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public enum UserType {

	ANONYMOUS(0),

	PUBLIC_USER(100),

	CUSTOMER(200),//本地用户级别

	AGENCY_USER(300),

	SUPER_ADMIN(400);



	/**
	 * Field value
	 */
	private int value;

	/**
	 * Constructs ...
	 *
	 *
	 * @param value
	 *            Integer
	 */
	private UserType(int value) {
		this.value = value;
	}

	/**
	 * Method getValue
	 *
	 *
	 * @return Integer
	 */
	public int getValue() {
		return value;
	}





	/**
	 * toString
	 *
	 * @return String
	 */
	public String getLabel() {
		String userType = "";
		switch (this) {
		case PUBLIC_USER:
			userType = "公网用户";
			break;
		case AGENCY_USER:
			userType = "机构用户";
			break;
		case SUPER_ADMIN:
			userType = "超级管理员";
			break;
		case ANONYMOUS:
			userType = "匿名用户";
			break;
		default:
			userType = "";
		}

		return userType;
	}

	/**
	 * 
	 * get UserType by index
	 *
	 * @param str
	 * @return
	 */
	public static UserType getUserType(int str) {
		UserType userType = null;
		for (UserType ut : UserType.values()) {
			if (ut.value == str) {
				userType = ut;
				break;
			}
		}
		return userType;
	}
	/**
	 * TODO.通过名称获取UserType
	 * @param str
	 * @return
	 */
	public static UserType getUserType(String str) {
		UserType userType = null;
		for (UserType ut : UserType.values()) {
			if (ut.name().equals(str)) {
				userType = ut;
				break;
			}
		}
		return userType;
	}

	public static void main(String[] args) {
		System.out.println(getUserType(3).getLabel());
	}

}

/*
 * $Log: av-env.bat,v $
 */