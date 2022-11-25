package org.typroject.tyboot.prototype.account.trade;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface TradeParams {


    /**是否必填*/
    boolean isNotnull();

    /**参数名*/
    String getParesStr();

    /**参数Code*/
    String getParamCode();

    <T> T getValue(Map<String,Object> params);
}
