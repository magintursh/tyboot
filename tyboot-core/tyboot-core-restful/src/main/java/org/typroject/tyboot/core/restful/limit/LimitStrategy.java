package org.typroject.tyboot.core.restful.limit;


import org.springframework.web.method.HandlerMethod;
import org.typroject.tyboot.core.auth.face.model.SsoSessionsModel;

/**
 * 请求限制策略,以固定时间窗口的方式限制请求，比较粗暴，但足以防止恶意操作。
 * 其他算法的限制方式后续再做
 */
public interface LimitStrategy {



    String CACHE_KEY_PREFIX = "REQUEST_LIMIT";

    /**
     * 频次
     * @return
     */
    Frequency getFrequency();


    /**
     * 限制策略的缓存计数key
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    String incrementKey(HandlerMethod handlerMethod);


    /**
     * 是否可执行限制策略
     * @return
     * @throws Exception
     */
    boolean isEnable(HandlerMethod handlerMethod) ;


    /**
     * 是否token认证之后执行
     * @return
     */
    boolean afterTokenAuth();


    /**
     * 所提供的限制策略
     * @return
     */
    Strategy getStrategy();


    /**
     * 是否针对单实体的限制，若为false，则是针对此类实体的全局限制。
     * @return
     */
    boolean singleEntityLimit();


    /**
     * 如果是针对单实体的限制，需要设置目标实体的标识
     */
    String getEntityId();

}
