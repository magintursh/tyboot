package org.typroject.tyboot.core.restful.auth.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.component.cache.Redis;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.auth.AuthHandler;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.exception.instance.TooManyRequests;
import org.typroject.tyboot.core.restful.interceptor.AuthInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * Created by yaohelang on 2018/6/27.
 */
public class RequestLimitAuthHandler implements AuthHandler,InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(RequestLimitAuthHandler.class);



    private RedisTemplate redisTemplate;



    enum LimitWay
    {
        userId,IP,token,device,API
    }



    //@Value("${freeapis.requestLimit.period}")
    private long requestLimitPeriod = 60L;

    //@Value("${freeapis.requestLimit.frequency.nonget}")
    private long globalNonGetLimit  = 10;

    //@Value("${freeapis.requestLimit.frequency.get}")
    private long globalGetLimit = 100;


    //@Value("${freeapis.requestLimit.frequency.nonget}")
    private long singleNonGetLimit  = 10;

    //@Value("${freeapis.requestLimit.frequency.get}")
    private long singleGetLimit = 100;


    private static String REQ_LIMIT = "REQ_LIMIT";



    public void afterPropertiesSet() throws Exception
    {

        redisTemplate = (RedisTemplate)SpringContextHelper.getBean(RedisTemplate.class);
        AuthInterceptor.addAuthHandler(this);
    }


    /**
     * 刷新session之前进行验证
     * @param handlerMethod
     * @param token
     * @param appKey
     * @param product
     */
    public void doAuth(HandlerMethod handlerMethod,
                String token, String appKey, String product)throws Exception
    {
        TycloudOperation tycloudOperation = handlerMethod.getMethodAnnotation(TycloudOperation.class);
        RequestMethod httpMethod = handlerMethod.getMethodAnnotation(RequestMapping.class).method()[0];

        //全局请求频次限制
        this.globalLimitByIp(httpMethod);
        this.globalLimitByToken(httpMethod);

        //单个接口请求频次限制

    }


    /**
     * 根据ip限制全局请求频次
     * @param httpMethod
     * @throws Exception
     */
    private  void globalLimitByIp(RequestMethod httpMethod) throws Exception
    {
        String key = Redis.genKey(REQ_LIMIT, RequestContext.getRequestIP());
        this.globalLimit(httpMethod,key);
    }


    /**
     * 根据Token限制全局请求频次
     * @param httpMethod
     * @throws Exception
     */
    private  void globalLimitByToken(RequestMethod httpMethod) throws Exception
    {
        if(!ValidationUtil.isEmpty(RequestContext.getToken()))
        {
            String key = Redis.genKey(REQ_LIMIT,RequestContext.getToken());
            this.globalLimit(httpMethod,key);
        }
    }

    private void globalLimit(RequestMethod httpMethod,String key)throws Exception
    {
        long requestCount = redisTemplate.opsForValue().increment(key,1L);
        if (requestCount == 1) {
            redisTemplate.expire(key, requestLimitPeriod, TimeUnit.SECONDS);
        }
        long limitCount = RequestMethod.GET.equals(httpMethod) ? globalGetLimit : globalNonGetLimit;

        if (requestCount > limitCount) {
            logger.info("用户IP[" + RequestContext.getRequestIP() + "];" +
                        "用户token:[" + RequestContext.getToken() + "];" +
                        "访问方式[" + httpMethod.name() + "]," +
                        "超过了限定的次数[" + limitCount + "]");
            throw new TooManyRequests("操作太过频繁，请稍后重试.");
        }
    }





    /**
     * 执行顺序
     * @return
     */
    public int  order()
    {
        return 1;
    }
}
