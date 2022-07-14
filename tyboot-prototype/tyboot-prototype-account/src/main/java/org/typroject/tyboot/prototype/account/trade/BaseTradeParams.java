package org.typroject.tyboot.prototype.account.trade;

import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.prototype.account.AccountTradeException;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
public class BaseTradeParams {

    /**
     * 解析和验证交易参数完整性
     * 1.解析参数列表
     * 2.检查参数完整性
     * 3.设置将要操作的账户信息
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static boolean checkPrams(Map<String, Object> params, TradeParams[] tradeParams)  {
        //验证必填参数
        if (!ValidationUtil.isEmpty(params)) {
            for (TradeParams tradeparam : tradeParams) {
                if (tradeparam.isNotnull() && ValidationUtil.isEmpty(params.get(tradeparam.getParamCode()))) {
                    throw new AccountTradeException("交易参数：" + tradeparam.getParamCode() + ";" + tradeparam.getParesStr() + "不能为空");
                }
            }
        } else {
            throw new AccountTradeException("参数列表不能为空.");
        }
        return true;
    }

    public static <T> T getValue(Map<String, Object> params, TradeParams tradeParams) {
        T value;
        value = (T) params.get(tradeParams.getParamCode());
        return value;
    }

}
