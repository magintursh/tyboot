package org.typroject.tyboot.core.restful.limit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.core.auth.face.model.SsoSessionsModel;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.auth.AuthHandler;
import org.typroject.tyboot.core.restful.auth.AuthWithSessionHandler;
import org.typroject.tyboot.core.restful.auth.ExtendAuthHandler;
import org.typroject.tyboot.core.restful.exception.instance.TooManyRequests;

import java.util.ArrayList;
import java.util.List;

//@Component
public class LimitStrategyManager implements  AuthWithSessionHandler, InitializingBean {


    private static List<LimitStrategy> restrictiveStrategyList = new ArrayList<>();


    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {
        ExtendAuthHandler.addAuthWithSessionHandler(this);
    }



    private void runRestrictiveStrategy(LimitStrategy restrictiveStrategy,HandlerMethod handlerMethod) throws Exception
    {
        String cacheKey         = restrictiveStrategy.incrementKey( handlerMethod);
        Object previousValue    = redisTemplate.opsForValue().get(cacheKey);

        Long increment = redisTemplate.opsForValue().increment(cacheKey);
        if(increment > restrictiveStrategy.getFrequency().getQuantity())
            throw new TooManyRequests("请求过于频繁，请稍后重试.");

        if(ValidationUtil.isEmpty(previousValue))
            redisTemplate.expire(cacheKey,restrictiveStrategy.getFrequency().getPeriod(),restrictiveStrategy.getFrequency().getTimeUnit());
    }


    @Override
    public void doAuth(SsoSessionsModel ssoSessionsModel,HandlerMethod handlerMethod, String token, String appKey, String product) throws Exception {
        if(!ValidationUtil.isEmpty(restrictiveStrategyList))
            for(LimitStrategy restrictiveStrategy:restrictiveStrategyList)
                    this.runRestrictiveStrategy(restrictiveStrategy,handlerMethod);
    }


    /**
     *  添加限制策略实现，
     *  系统实现的限制策略默认不启用，可以自行配置
     * @param restrictiveStrategy
     */
    public static  void addRestrictiveStrategy(LimitStrategy restrictiveStrategy)    {
        restrictiveStrategyList.add(restrictiveStrategy);
    }
}
