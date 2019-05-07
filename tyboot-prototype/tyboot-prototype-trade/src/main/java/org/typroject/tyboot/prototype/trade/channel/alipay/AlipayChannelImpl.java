package org.typroject.tyboot.prototype.trade.channel.alipay;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;
import org.typroject.tyboot.face.trade.model.TransactionsSerialModel;
import org.typroject.tyboot.prototype.trade.Trade;
import org.typroject.tyboot.prototype.trade.TradeResultModel;
import org.typroject.tyboot.prototype.trade.TradeStatus;
import org.typroject.tyboot.prototype.trade.TradeType;
import org.typroject.tyboot.prototype.trade.channel.BaseChannelProcess;
import org.typroject.tyboot.prototype.trade.channel.ChannelProcessor;

import java.util.Map;

@Component(value = "alipayChannel")
public class AlipayChannelImpl extends BaseChannelProcess implements ChannelProcessor {

	private final Logger logger = LogManager.getLogger(AlipayChannelImpl.class) ;

	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmH6OBWjZy6cM8gr9eu+XX/5DOfa18rztc96MtywuWXA5KkW888X9xm6yZQ4ZQApuVpur++I2lWFovuc6ADgt/mc8aN4zI+DoCafecLS4wp1PTkV7HYGI//V91XZZbi6vKcqeqx1wuPH1h1okSDnElkBlFN68ldALWMOXCWcdKkvoJ9VTPKBX+IxrUio52fBOiQm6RP+uirw1S3rbq6r9/PqXC8z3OQiD5UHCHSmexUPF1OJKsGhxHYOSIc5Cwl9iCWnMNQU6PBIG/+HTyZoBt3rHjH0diPbjjCCmMdvfdA6L/wT2fCFBBHQKRZDnAJIEvYehs/+efAVD57G2a2MDJQIDAQAB";


	private static final String CHANNEL_PIX = "alipay";
	
	@Override
	public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType, Map<String, Object> extraParams)
			throws Exception {
		Trade trade = (Trade)SpringContextHelper.getBean(CHANNEL_PIX+tradeType.getTradeProcessor());
		TradeResultModel resultModel =  trade.process(serialModel, extraParams);
		return resultModel;
	}


	public TradeResultModel processTradeResult(String serialNo , TradeStatus tradeStatus, Object result)
			throws Exception {

		TradeResultModel resultModel = new TradeResultModel();//交易结果

		//获取支付宝POST过来反馈信息
		Map<String,String> params = (Map)result;

	//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
	//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, AlipayConstants.CHARSET_UTF8,"RSA2");
		resultModel.setCalledSuccess(flag);
		return resultModel;
	}

}
