package org.typroject.tyboot.core.restful.doc;

import org.typroject.tyboot.core.restful.utils.APILevel;

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
	 * 
	 * 单个token单位时间内允许访问的次数，默认值MAX_VALUE
	 */
	int limit_count() default 100;

	/**
	 * 
	 * 时间段，单位为秒，默认值一分钟
	 */
	long limit_time() default 60;
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
	APILevel ApiLevel();
}

/*
*$Log: av-env.bat,v $
*/