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


	//private static final String APP_PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApkNM2JG8FqLMHhbyrJUKjQmXnwcz/nOiXFnYLC70JnX3phg8eJL+lgLeyg5hKOpDxN8dbw/A3+66HzEXN2cSN63iPMVnWUkjAVKtunnoEakKMWO1BCFPG4WZ88PQs5P1sR9j6f8qCXfb8xrYBdlYe45F6AUwaeUkI1shzn/FzzzgdCFp+YWSfVd0uqNCm9Iy4yVBIRyjw8um8hUGreiccpBkGHatYjNKhp61Kpp4CF7IJUZp5Ab+sX9VPOOsSuJiKiqL2hvg+9zhCl9TMDNEDjIpUXzyd0BwU7A7IzODKXDwz1IE+HmQrVhTJXrsJoORrqWzE+UJo7yn4k6h6OEAJQIDAQAB";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmH6OBWjZy6cM8gr9eu+XX/5DOfa18rztc96MtywuWXA5KkW888X9xm6yZQ4ZQApuVpur++I2lWFovuc6ADgt/mc8aN4zI+DoCafecLS4wp1PTkV7HYGI//V91XZZbi6vKcqeqx1wuPH1h1okSDnElkBlFN68ldALWMOXCWcdKkvoJ9VTPKBX+IxrUio52fBOiQm6RP+uirw1S3rbq6r9/PqXC8z3OQiD5UHCHSmexUPF1OJKsGhxHYOSIc5Cwl9iCWnMNQU6PBIG/+HTyZoBt3rHjH0diPbjjCCmMdvfdA6L/wT2fCFBBHQKRZDnAJIEvYehs/+efAVD57G2a2MDJQIDAQAB";
	private static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMiyEju+dWaMJBijaaAFWT9uU8NwLalWwScPOBn9el0SMv70IO6XmPxzfDO3R43vCTCP8731yxhuHq/wjQr4vfBqfYl/GOJOh+id2JOwJE8s+b4XQV0r6WQ4K+TjY4icWpAl0yI+c/9Y5T7n9wInaLGny3BwDKRbaHEzHJNXWlaIsTLoDe+5RFMilyA2H185zes5gpHgzqTuBm39141ywgZn29pm46N83IPUVK0gDitVdlQj7C+xto5vy5evkOEGrkckFycpDzFLGP383WSctVlFQ7o/AQBKBbiNz5Cl9PzJgfBRwX1XUImsBGRVfLFbWvyeeX+VSNQzrzkgq1B4VzAgMBAAECggEAemc6B5tBkfpPJTGw+FNONT4UzGrE64azxyPkkIKcGGibEsZ4a/Bf1R8Wq8x+Vj8km4p8cLLKUjs9S6OvE8GwOh/b00ISOVBfxMVh0oE4KsCmnAaxSchwAQLvPbt0ca1cHyRV8rNeVlARE3xmNU1unRwGfol04LXykJXhCHKs8c9aVHhCZXtZMv2yDXfc1/XPTh4aM7DD1BFYGWuVJVoHrehH76USN9O/eCHYi8uT85QLbkE1aL8a2bRhSXCuaNbL1ji7dQ7FJB0uzpeioOLKYgZhCmhyyaKGAJKa7T4XJpWq5M/8v7r+/h7lq7dXJlZJOS2dHaBw9DvPem6e431cEQKBgQDfwtdczVkBbsPooNjHZ8/8wvjwSzMCcSrHNFAaUVr/2FUOxTmoYSnZkxM/wQ/c1C6EE9yYlMvHJd40f5CynvemejLtdxaI767MirHZuO9RH5ZYmjecrLTBfTzJr6NUF4aMeVd43kYBJH3AEILQW/2EF6sIky1ah/+L61l+YH2otwKBgQCgyugDsAyyXcTNQgnhDXxjf0PRPP9qEtuVLg9aBP+XKVhsl/kVxKXCbHfoG8rX0q15M8G4eIcGAJrGvzicxiyB8QD8mrIgTz1M56gaoVos+Vdf/pAVoLvh1SLJr+QofIrAHbjlqxH3MQrD78UeFvAnHpRxJ8YEkQQxroTFVdH1JQKBgAwsACUXMolGajfEUCaAuiqovHvty0eNumL9sIHN/SI/tVjJV7qWFn20Z26nZOKBVEpw/iX6qh8aKz011zwwM46FxTFvsfUe/KY5wTTzv2jtS/Nj6DSInIiN9HmQKVVQtkv76cs0ZCB5yLFi9Iq7Bk5iLzaXMvdiwH7u/X5HZ25/AoGAF16GLAsJPV89fCuN7gScwIU9oQqBgLQu79aa6pVxZFHhBR9tV0mWYdSL9NMdPxLinYv1Ks9xqGCsHhiqrLZsv5H8d7owC1wY57V/jCdJSOIQXeZMoKgUxnTIspF60puPOu7W8aJUYR3il6mgd4gT7UuXfX6vW/iLqIDZ0Shw9HUCgYA15+PuNb80IJwtaS4IA2lvmVQgNt9HuSdYS9zMhcBLRDicmSvUmZydxORV7Fu708nS5nvduG1ffSeBzJuPuGzt5UqToJceevxf8xjsURMl6n9N346O950KzZ9sunQMuMvkO7shH3mYmef9czB0P73oqaIx6K5Ejg2NWW3xCmHGXA==";


	@Value("${domain_url}")
	private String domainUrl;

	@Autowired
	private TransactionsSerialService transactionsSerialService;


	@Override
	public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception {

		TradeResultModel resultModel  = new TradeResultModel();

		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2018012902105933", APP_PRIVATE_KEY, "json", AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY, "RSA2");
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
