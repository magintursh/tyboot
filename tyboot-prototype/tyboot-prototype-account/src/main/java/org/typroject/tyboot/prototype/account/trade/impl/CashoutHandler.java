package org.typroject.tyboot.prototype.account.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.face.account.model.AccountCashoutRecordModel;
import org.typroject.tyboot.face.account.service.AccountCashoutRecordService;
import org.typroject.tyboot.face.account.service.AccountTransferRecordService;
import org.typroject.tyboot.prototype.account.Account;
import org.typroject.tyboot.prototype.account.AccountConstants;
import org.typroject.tyboot.prototype.account.AccountTradeException;
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
 *  File: CashoutHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  凍結指定賬戶金額
 * 
 *  Notes:
 *  $Id: CashoutHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "cashoutHandler")
public class CashoutHandler  implements AccountTradeHandler {



	@Autowired
	private AccountTransferRecordService accountTransferRecordService;

	@Autowired
	private AccountCashoutRecordService accountCashoutRecordService;



	/**交易参数*/
	private enum CashoutParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户賬單
		applyStatus(false,"提現申請狀態"),
		amount(true,"交易金额"),	   //交易金额
		postscript(true,"交易附言"); //附言
		
		private boolean notnull;
		private String paramName;
		CashoutParams(boolean notnull,String paramName)
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
	public boolean execute(Map<String, Object> params,Account account){
		boolean flage = false;
		 if(BaseTradeParams.checkPrams(params, CashoutParams.values()))
		 {
			 // #1.驗證提現記錄和其凍結記錄
			 String billNo						  = CashoutParams.billNo.getValue(params);
			 BigDecimal amount					  = CashoutParams.amount.getValue(params);
			 String  postscript					  = CashoutParams.postscript.getValue(params);
			 String applyStatus					  = CashoutParams.applyStatus.getValue(params);
			 AccountCashoutRecordModel cashoutRecord   = accountCashoutRecordService.queryByApplyNo(billNo);

			 if( !ValidationUtil.isEmpty(cashoutRecord)
					 &&	amount.doubleValue() == cashoutRecord.getApplayAmount().doubleValue()
					)
			 {
				// #2.更新提現記錄--默認是手動確認提現
				 cashoutRecord.setApplyStatus(ValidationUtil.isEmpty(applyStatus)? AccountConstants.CASHOUT_STATUS_TRANSFERRED:applyStatus);
				 accountCashoutRecordService.updateWithModel(cashoutRecord);
				 // #3.將凍結賬戶的對應的提現凍結金額做出賬處理\
				 account.spend(amount,DefaultAccountTradeType.CASH_OUT,billNo,postscript);
			 }else{
				 throw new AccountTradeException("提現交易參數有誤.");
			 }
		 }
		return flage;	
	}
	
	

}