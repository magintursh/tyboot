package org.typroject.tyboot.core.restful.auth;

import org.springframework.web.method.HandlerMethod;

/**
 * Created by yaohelang on 2018/6/27.
 */
public interface AuthHandler {


    /**
     * 刷新session之前执行
     * @param handlerMethod
     * @param token
     * @param appKey
     * @param product
     * @throws Exception
     */
    void doAuth(HandlerMethod handlerMethod, String token, String appKey, String product)throws Exception;
}
