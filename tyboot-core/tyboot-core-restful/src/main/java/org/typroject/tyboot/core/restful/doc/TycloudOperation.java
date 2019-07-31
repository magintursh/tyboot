package org.typroject.tyboot.core.restful.doc;

import org.typroject.tyboot.core.foundation.enumeration.UserType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * <pre>
 *  Tyrest
 *  File: TycloudOperation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TycloudOperation.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TycloudOperation {
	/**
	 * 是否需要验证
	 * TODO.
	 *
	 * @return
	 */
	boolean needAuth() default true;

	/**
	 * API可见级别 PUBLIC,AGENCY,ALL,SUPERADMIN
	 */
	UserType ApiLevel();
}

/*
*$Log: av-env.bat,v $
*/