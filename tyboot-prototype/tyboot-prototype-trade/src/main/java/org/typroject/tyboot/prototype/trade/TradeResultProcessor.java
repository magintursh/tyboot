package org.typroject.tyboot.prototype.trade;


import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
public interface TradeResultProcessor {

    TradeResultModel processResult(String billNo, String serialNo, Map<String, Object> infoMap) throws Exception;
}
