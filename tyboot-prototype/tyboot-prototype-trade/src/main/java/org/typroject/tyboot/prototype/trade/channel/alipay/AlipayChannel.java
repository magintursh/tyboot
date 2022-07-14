package org.typroject.tyboot.prototype.trade.channel.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;
import org.typroject.tyboot.face.trade.model.TransactionsSerialModel;
import org.typroject.tyboot.prototype.trade.*;
import org.typroject.tyboot.prototype.trade.channel.BaseChannelProcess;
import org.typroject.tyboot.prototype.trade.channel.ChannelProcessor;
import org.typroject.tyboot.prototype.trade.channel.alipay.trade.AlipayPayment;

import java.util.Map;

@Component(value = "alipayChannel" )
public class AlipayChannel extends BaseChannelProcess implements ChannelProcessor {
    private static final Logger logger = LoggerFactory.getLogger(AlipayChannel.class);

    @Autowired
    private AlipayProperty alipayProperty;

    @Override
    public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType, Map<String, Object> extraParams) {
        Trade trade = (Trade) SpringContextHelper.getBean(tradeType.getTradeProcessor());
        TradeResultModel resultModel = trade.process(serialModel, extraParams);
        return resultModel;
    }


    public TradeResultModel processTradeResult(String serialNo, TradeStatus tradeStatus, Object result) {

        TradeResultModel resultModel = new TradeResultModel();//交易结果

        //获取支付宝POST过来反馈信息
        Map<String, String> params = (Map) result;

        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean flag = false;
        try {
            flag = AlipaySignature.rsaCheckV1(params, alipayProperty.getPublicKey(), AlipayConstants.CHARSET_UTF8, "RSA2" );
        } catch (AlipayApiException e) {
            logger.error(e.getErrMsg(),e);
            throw new TradeException("errCode："+e.getErrCode()+";errMsg："+e.getErrMsg());
        }
        resultModel.setCalledSuccess(flag);
        return resultModel;
    }
}
