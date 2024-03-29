package org.typroject.tyboot.prototype.account.trade.impl;

import org.springframework.stereotype.Component;
import org.typroject.tyboot.prototype.account.Account;
import org.typroject.tyboot.prototype.account.trade.AccountTradeHandler;
import org.typroject.tyboot.prototype.account.trade.BaseTradeParams;
import org.typroject.tyboot.prototype.account.trade.DefaultAccountTradeType;
import org.typroject.tyboot.prototype.account.trade.TradeParams;

import java.math.BigDecimal;
import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: PaymentHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PaymentHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "paymentHandler")
public class PaymentHandler  implements AccountTradeHandler {

	/**交易参数*/
	 enum PaymentParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户
		amount(true,"交易金额"),  //交易金额
		postscript(true,"交易附言"); //附言
		
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
			BigDecimal amount = PaymentParams.amount.getValue(params);
			String billNo     = PaymentParams.billNo.getValue(params);
			 String postscript     = PaymentParams.postscript.getValue(params);
			//执行交易
			flage = account.spend(amount, DefaultAccountTradeType.PAYMENT,billNo,postscript);
			
			//验证结果			 
		 }	 
		return flage;	
	}
	
	

}