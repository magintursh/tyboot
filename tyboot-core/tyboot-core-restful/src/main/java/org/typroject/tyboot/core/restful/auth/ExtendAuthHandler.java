package org.typroject.tyboot.core.restful.auth;

import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.core.auth.face.model.SsoSessionsModel;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;

import java.util.SortedMap;
import java.util.TreeMap;

public class ExtendAuthHandler  {


    private static SortedMap<Integer,AuthHandler> authHandlers                      = new TreeMap<>();
    private static SortedMap<Integer,AuthWithSessionHandler> authWithSessionHandlers = new TreeMap<>();

    private static final long duration = 10L;//每个验证规则的执行时间不能超过10毫秒


    public static void doAuth(HandlerMethod handlerMethod, String token, String appKey, String product) throws Exception {


        //扩展的验证规则
        if(!ValidationUtil.isEmpty(authHandlers))
            for(Integer key:authHandlers.keySet())
            {
                long time = System.currentTimeMillis();
                authHandlers.get(key).doAuth(handlerMethod,token,appKey,product);
                long currentDuration = System.currentTimeMillis() - time;
                if(currentDuration > duration)
                    throw new Exception("扩展验证规则执行时间过长.");
            }

    }



    public static void doAuthWithSession(SsoSessionsModel ssoSessionsModel, HandlerMethod handlerMethod, String token, String appKey, String product) throws Exception {

        //扩展的验证规则
        if(!ValidationUtil.isEmpty(authWithSessionHandlers))
            for(Integer key :authWithSessionHandlers.keySet())
            {
                long time = System.currentTimeMillis();
                authWithSessionHandlers.get(key).doAuth(ssoSessionsModel,handlerMethod,token,appKey,product);
                long currentDuration = System.currentTimeMillis() - time;
                if(currentDuration > duration)
                    throw new Exception("扩展验证规则执行时间过长.");
            }

    }



    public static void addAuthHandler(AuthHandler authHandler) throws Exception
    {
        if(!ValidationUtil.isEmpty(authHandlers.get(authHandler.order())))
            throw new Exception("the order of "+authHandler.order()+ " have bean exist");
        authHandlers.put(authHandler.order(),authHandler);
    }

    public static void addAuthWithSessionHandler(AuthWithSessionHandler authWithSessionHandler) throws Exception
    {
        if(!ValidationUtil.isEmpty(authWithSessionHandlers.get(authWithSessionHandler.order())))
            throw new Exception("the order of "+authWithSessionHandler.order()+ " have bean exist");
        authWithSessionHandlers.put(authWithSessionHandler.order(),authWithSessionHandler);
    }

}
