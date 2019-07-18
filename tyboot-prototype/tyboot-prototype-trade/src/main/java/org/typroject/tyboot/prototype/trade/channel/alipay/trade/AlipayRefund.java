package org.typroject.tyboot.prototype.trade.channel.alipay.trade;

import com.alipay.api.*;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.exception.BaseException;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.face.trade.model.TransactionsSerialModel;
import org.typroject.tyboot.face.trade.service.TransactionsSerialService;
import org.typroject.tyboot.prototype.trade.Trade;
import org.typroject.tyboot.prototype.trade.TradeResultModel;
import org.typroject.tyboot.prototype.trade.constants.TradeConstants;

import java.math.RoundingMode;
import java.util.Map;

@Component(value = "alipayRefund")
public class AlipayRefund implements Trade {

	
	private static final Logger logger = LoggerFactory.getLogger(AlipayRefund.class);


	private static final String ALIPAY_PUBLIC_KEY = "";
	private static final String APP_PRIVATE_KEY = "";


	@Value("${domain_url}")
	private String domainUrl;

	@Autowired
	private TransactionsSerialService transactionsSerialService;


	@Override
	public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception {

		TradeResultModel resultModel  = new TradeResultModel();

		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "xxxxxxx", APP_PRIVATE_KEY, "json", AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.refund

		AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
		String oldSerialNo = (String)extra.get(TradeConstants.SERIALNO);
		TransactionsSerialModel oldSerialModel = this.transactionsSerialService.selectBySeriaNo(oldSerialNo);
		if(!ValidationUtil.isEmpty(oldSerialModel))
		{

			RefundModel model = new RefundModel();
			model.setOut_trade_no(oldSerialNo);
			model.setRefund_amount(serialModel.getTradeAmount().setScale(2, RoundingMode.HALF_EVEN).toString());
			model.setOut_request_no(serialModel.getSerialNo());
			refundRequest.setBizContent(model.toString());
			//refundRequest.setNotifyUrl(domainUrl+"/apis/v1/trade/payment/ailpay");
			try {
				//这里和普通的接口调用不同，使用的是sdkExecute
				AlipayTradeRefundResponse response = alipayClient.execute(refundRequest);
				if(response.isSuccess())
				{
					resultModel.setResult("SUCCESS");
					resultModel.setCalledSuccess(true);
				}else
				{
					throw new BaseException("退款失败.稍后重试.","tradeException","交易失败.");
				}
				//System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。

			} catch (AlipayApiException e) {
				logger.error(e.getErrMsg(),e);
				throw e;
			}

		}

//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的tradeAmount = {BigDecimal@10388} "1.00"model入参方式(model和biz_content同时存在的情况下取biz_content)。


		return resultModel;
	}



	class RefundModel extends AlipayObject
	{
		private String out_trade_no;
		private String refund_amount;
		private String out_request_no;

		public String getOut_request_no() {
			return out_request_no;
		}

		public void setOut_request_no(String out_request_no) {
			this.out_request_no = out_request_no;
		}

		public String getOut_trade_no() {
			return out_trade_no;
		}

		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}

		public String getRefund_amount() {
			return refund_amount;
		}

		public void setRefund_amount(String refund_amount) {
			this.refund_amount = refund_amount;
		}

		public String toString()
		{
			return "{" +
					"\"out_trade_no\":\""+out_trade_no+"\"," +
					"\"refund_amount\":"+refund_amount+"," +
					"\"out_request_no\":\""+out_request_no+"\"" +
					"}";
		}
	}




}
