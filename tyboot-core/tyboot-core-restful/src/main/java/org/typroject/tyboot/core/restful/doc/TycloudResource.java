package org.typroject.tyboot.core.restful.doc;

import java.lang.annotation.*;

import io.swagger.annotations.Authorization;
import org.springframework.core.annotation.AliasFor;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TycloudResource.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TycloudResource.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TycloudResource {

	String module();

    @AliasFor("value")
    String resource() default "";

    @AliasFor("resource")
    String value() default "";

    String description() default "";
}

/*
*$Log: av-env.bat,v $
*/