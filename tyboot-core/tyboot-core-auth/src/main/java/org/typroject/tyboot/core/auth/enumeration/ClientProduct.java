package org.typroject.tyboot.core.auth.enumeration;

import org.typroject.tyboot.core.foundation.enumeration.UserType;

/**
 * description: ClientProduct <br>
 * date: 2022/7/17 11:24 <br>
 * author: wangjinyi <br>
 * version: 1.0 <br>
 */
public interface ClientProduct {

    /**
     * 外部平台名称
     * @return
     */
    String getProduct();

    /**
     * 支持的用户类型
     * @return
     */
    UserType[] userTypes();


}
