package org.typroject.tyboot.core.restful.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.context.RequestContext;

/**
 * <pre>
 *
 *  Tyrest
 *  File: RestEventInterceptor.java
 *
 *  Tyrest, Inc.
 *  Copyright (C): 2015
 *
 *  Description:系统的业务事件拦截器
 *  TODO 用于拦截所有的业务事件，并交给监听器处理
 *
 *  Notes:
 *  $Id: RestEventInterceptor.java 31101200-9 2014-10-14 16:43:51Z Tyrest\magintursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月23日		magintursh		Initial.
 *
 * </pre>
 */
@Aspect
@Order(value = 1)
@Component
public class RestResourceInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RestResourceInterceptor.class);


    /*定义切入点，所有那些触发业务事件的方法都会成为切入点*/
    @Pointcut("@annotation(org.typroject.tyboot.core.restful.doc.TycloudOperation)")
    public void restEventOccured() {
    }

    /*在触发业务事件的方法执行完之后触发系统业务事件*/
    @Around("restEventOccured()")
    public Object fireEvent(ProceedingJoinPoint pjp) throws Exception {
        long time = System.currentTimeMillis();
        Object retVal;
        try
        {
            retVal = pjp.proceed();
        }catch (Throwable throwable)
        {
            throwable.printStackTrace();
            throw new Exception(throwable.getMessage());
        }

        logger.info("请求耗时-TRACEID："+RequestContext.getTraceId()+" : "+(System.currentTimeMillis()-time));
        return retVal;
    }
}

/*
*$Log: av-env.bat,v $
*/