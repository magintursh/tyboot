package org.typroject.tyboot.prototype.trade.channel.cash;

import org.springframework.stereotype.Component;
import org.typroject.tyboot.face.trade.model.TransactionsSerialModel;
import org.typroject.tyboot.prototype.trade.TradeResultModel;
import org.typroject.tyboot.prototype.trade.TradeStatus;
import org.typroject.tyboot.prototype.trade.TradeType;
import org.typroject.tyboot.prototype.trade.channel.ChannelProcessor;

import java.util.Map;

@Component(value = "cashChannel")
public class CashChannelImpl implements ChannelProcessor {

    @Override
    public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType, Map<String, Object> extraParams) {
        return null;
    }

    @Override
    public TradeResultModel processTradeResult(String serialNo, TradeStatus tradeStatus, Object result) {
        return null;
    }
}
