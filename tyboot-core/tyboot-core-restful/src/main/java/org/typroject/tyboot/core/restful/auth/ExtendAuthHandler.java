package org.typroject.tyboot.core.restful.auth;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.core.auth.face.model.SsoSessionsModel;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.auth.impl.UserTypeAuthHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class ExtendAuthHandler implements InitializingBean {

    //这里边的验证规则，每个请求都会执行，请慎重所添加的验证器数量，和执行时间，执行顺序为添加的顺序
    private static List<AuthHandler> authHandlers                       = new ArrayList<>();
    private static List<AuthWithSessionHandler> authWithSessionHandlers = new ArrayList<>();

    private static final long duration = 10L;//每种验证规则的执行时间不能超过10毫秒


    @Autowired
    private UserTypeAuthHandler userTypeAuthHandler;


    @Override
    public void afterPropertiesSet() throws Exception {

        authWithSessionHandlers.add(userTypeAuthHandler);
    }

    public static void doAuth(SsoSessionsModel ssoSessionsModel, HandlerMethod handlerMethod, String token, String appKey, String product) throws Exception {


        //扩展的验证规则
        long time = System.currentTimeMillis();
        if(!ValidationUtil.isEmpty(authWithSessionHandlers))
            for(AuthWithSessionHandler authHandler :authWithSessionHandlers)
                authHandler.doAuth(ssoSessionsModel,handlerMethod,token,appKey,product);

        long currentDuration = System.currentTimeMillis() - time;
        if(currentDuration > duration)
            throw new Exception("扩展验证规则执行时间过长，超过了"+duration+"毫秒.当前执行时间为："+currentDuration);
    }


    public static void doAuth(HandlerMethod handlerMethod, String token, String appKey, String product) throws Exception {


        //扩展的验证规则
        long time = System.currentTimeMillis();
        if(!ValidationUtil.isEmpty(authHandlers))
            for(AuthHandler authHandler :authHandlers)
                authHandler.doAuth(handlerMethod,token,appKey,product);

        long currentDuration = System.currentTimeMillis() - time;
        if(currentDuration > duration)
            throw new Exception("扩展验证规则执行时间过长，超过了"+duration+"毫秒.当前执行时间为："+currentDuration);

    }


    public static void addAuthHandler(AuthHandler authHandler)
    {
        authHandlers.add(authHandler);
    }

    public static void addAuthWithSessionHandler(AuthWithSessionHandler authWithSessionHandler)
    {
        authWithSessionHandlers.add(authWithSessionHandler);
    }

}
