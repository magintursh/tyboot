package org.typroject.tyboot.prototype.account.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.face.account.model.AccountExchangeRateModel;
import org.typroject.tyboot.face.account.service.AccountExchangeRateService;
import org.typroject.tyboot.prototype.account.Account;
import org.typroject.tyboot.prototype.account.AccountTradeException;
import org.typroject.tyboot.prototype.account.trade.AccountTradeHandler;
import org.typroject.tyboot.prototype.account.trade.BaseTradeParams;
import org.typroject.tyboot.prototype.account.trade.DefaultAccountTradeType;
import org.typroject.tyboot.prototype.account.trade.TradeParams;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 虚拟账户货币兑换
 */
@Component(value = "paymentHandler")
public class ExchangeHandler implements AccountTradeHandler {


	@Autowired
	AccountExchangeRateService exchangeRateService;


	/**交易参数*/
	 enum PaymentParams implements TradeParams {

		billNo(true,"账单号"),
		amount(true,"兑换金额"),
		postscript(true,"交易附言"),
		targetAccountNo(true,"目标账户编号");

		private boolean notnull;
		private String paramName;
		PaymentParams(boolean notnull,String paramName)
		{
			this.notnull = notnull;
			this.paramName = paramName; 
		}  
		public boolean isNotnull()
		{
			return notnull;
		}
		public String getParesStr()
		{
			return paramName;
		}
		public String getParamCode(){return this.name();}
		@Override
		public <T> T getValue(Map<String, Object> params) {
			return (T) params.get(this.getParamCode());
		}


	}

	
	
	@Override
	public boolean execute(Map<String, Object> params,Account account)  {
		boolean flage = false;
		//解析参数
		 if(BaseTradeParams.checkPrams(params, PaymentParams.values()))
		 {
			 BigDecimal amount 		   = PaymentParams.amount.getValue(params);
			 String targetAccountNo = PaymentParams.targetAccountNo.getValue(params);
			 String billNo		   =  PaymentParams.billNo.getValue(params);
			 String postscript	   =  PaymentParams.postscript.getValue(params);
			 Account targetAccount  = Account.getAccountInstance(targetAccountNo);

			 BigDecimal targetRechargeAmount = BigDecimal.ZERO;

			 AccountExchangeRateModel exchangeRateModel  // 汇率正向
					 = exchangeRateService.queryByAccountType(
					 		account.getAccountInfoModel().getAccountType(),
					 		targetAccount.getAccountInfoModel().getAccountType());

			 AccountExchangeRateModel inverseExchangeRate  //汇率，逆向
					 = exchangeRateService.queryByAccountType(
					 targetAccount.getAccountInfoModel().getAccountType(),
					 account.getAccountInfoModel().getAccountType());

		if(ValidationUtil.isEmpty(exchangeRateModel) && ValidationUtil.isEmpty(inverseExchangeRate)){
			throw new AccountTradeException("未定义的汇率");
		}else if(!ValidationUtil.isEmpty(exchangeRateModel)){
			targetRechargeAmount = amount.multiply(exchangeRateModel.getExchangeRate()).setScale(2, RoundingMode.HALF_EVEN);
		}else if(!ValidationUtil.isEmpty(inverseExchangeRate)){
			targetRechargeAmount = amount.multiply(inverseExchangeRate.getInverseExchangeRate()).setScale(2, RoundingMode.HALF_EVEN);
		}

		if(targetRechargeAmount.doubleValue() <= 0){
			throw new AccountTradeException("汇率不存在或兑换结果为0");
		}


			//执行交易
			flage = account.spend(amount, DefaultAccountTradeType.EXCHANGE,billNo,postscript);

			 flage = flage && targetAccount.income(targetRechargeAmount, DefaultAccountTradeType.EXCHANGE,billNo,postscript);

			 //TODO 添加兑换记录
		 }	 
		return flage;	
	}
	
	

}