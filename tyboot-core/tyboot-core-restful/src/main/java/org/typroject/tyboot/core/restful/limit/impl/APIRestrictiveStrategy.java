package org.typroject.tyboot.core.restful.limit.impl;

import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.component.cache.Redis;
import org.typroject.tyboot.component.cache.enumeration.CacheType;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.limit.Frequency;
import org.typroject.tyboot.core.restful.limit.LimitStrategy;
import org.typroject.tyboot.core.restful.limit.Strategy;

import java.util.concurrent.TimeUnit;


public class APIRestrictiveStrategy implements LimitStrategy {


    //默认每分钟 每个API 最多发起100个请求
   private   Frequency frequency = new Frequency(TimeUnit.MINUTES,1L,100L,100L);;

   private Strategy extendStrategy = Strategy.USERID; //附加限制策略

   private static final String CACHE_KEY_PREFIX_API = "API";




   public APIRestrictiveStrategy()
   {

   }

    public APIRestrictiveStrategy(Frequency frequency) {
        this.frequency = frequency;
    }

    public APIRestrictiveStrategy(Frequency frequency, Strategy extendStrategy)
    {
        this.frequency = frequency;
        this.extendStrategy  = extendStrategy;
    }

    @Override
    public String  incrementKey(HandlerMethod handlerMethod) {

        String methodName = handlerMethod.getMethod().getClass().getSimpleName() +"."+ handlerMethod.getMethod().getName();

        String strategyKey  = getExtendStrategyStrategyKey();
        return Redis.genKey(
                CacheType.ERASABLE.name(),
                CACHE_KEY_PREFIX,
                strategyKey,
                methodName);
    }

    @Override
    public Frequency getFrequency() {
        return frequency;
    }


    private String getExtendStrategyStrategyKey()
    {
        String strategyKey  = "";
        switch (extendStrategy)
        {
            case IP:
                strategyKey = RequestContext.getRequestIP();
                break;
            case USERID:
                strategyKey = RequestContext.getExeUserId();
                break;
            case PRODUCT:
                strategyKey = RequestContext.getProduct();
                break;
            case TOKEN:
                strategyKey = RequestContext.getToken();
                break;
            case APPKEY:
                strategyKey = RequestContext.getAppKey();
                break;
            case LOGINID:
                strategyKey = RequestContext.getLoginId();
                break;
        }
        return Redis.genKey(Strategy.API.name(),strategyKey);
    }
    
    @Override
    public boolean isEnable(HandlerMethod handlerMethod) {

        TycloudOperation tycloudOperation = handlerMethod.getMethod().getAnnotation(TycloudOperation.class);
        if(!ValidationUtil.isEmpty(tycloudOperation) && tycloudOperation.enableLimitStrategy())
            return true;
        return false;
    }


    @Override
    public boolean afterTokenAuth() {
        return true;
    }

    @Override
    public Strategy getStrategy() {
        return Strategy.API;
    }

    @Override
    public boolean singleEntityLimit() {
        return false;
    }

    @Override
    public String getEntityId() {
        return null;
    }
}
