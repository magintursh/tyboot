package org.typroject.tyboot.prototype.trade.channel.alipay;

import org.typroject.tyboot.prototype.trade.Trade;
import org.typroject.tyboot.prototype.trade.TradeType;

/**
 * description: AlipayTradeType <br>
 * date: 2022/7/12 13:59 <br>
 * author: wangjinyi <br>
 * version: 1.0 <br>
 */
public enum  AlipayTradeType implements TradeType {


    APP_PAY("支付宝app支付",null)
    ;


    private String parseString;
    private Class<? extends Trade>  tradeProcessor;


    AlipayTradeType(String parseString, Class<? extends Trade> tradeProcessor) {
        this.parseString = parseString;
        this.tradeProcessor = tradeProcessor;
    }

    @Override
    public String getType() {
        return this.name();
    }

    @Override
    public Class<? extends Trade>  getTradeProcessor() {
        return null;
    }

    @Override
    public String parseString() {
        return null;
    }
}
