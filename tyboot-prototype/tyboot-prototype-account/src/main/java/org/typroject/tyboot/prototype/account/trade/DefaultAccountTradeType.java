package org.typroject.tyboot.prototype.account.trade;


import org.typroject.tyboot.prototype.account.AccountOperateRule;
import org.typroject.tyboot.prototype.account.rule.AccountIncomeRule;
import org.typroject.tyboot.prototype.account.rule.AccountSpendRule;
import org.typroject.tyboot.prototype.account.trade.impl.CashoutHandler;
import org.typroject.tyboot.prototype.account.trade.impl.PaymentHandler;
import org.typroject.tyboot.prototype.account.trade.impl.RechargeHandler;
import org.typroject.tyboot.prototype.account.trade.impl.TransferHandler;

/**
 * 系统自身所支持的交易类型
 */
public enum DefaultAccountTradeType implements AccountTradeType {


	/**
	 *	支付
	 */
	PAYMENT("支付", PaymentHandler.class, AccountSpendRule.class),
	
	/**
	 * 从法币账户充值到虚拟账户
	 */
	RECHARGE("充值", RechargeHandler.class, AccountIncomeRule.class),
	
	/**
	 * 从虚拟账户提现到外部法币账户
	 */
	CASH_OUT("提现", CashoutHandler.class,AccountSpendRule.class),

	
	/**
	 * 用戶閒内部賬戶轉賬
	 */
	TRANSFER_INTERNAL ("内部转账", TransferHandler.class,AccountSpendRule.class);
	
	
	
	
	private String operationName;
	private Class<? extends AccountTradeHandler>  accountTradeHandler;
	private Class<? extends AccountOperateRule> operationRule;
	
	DefaultAccountTradeType(String operationName ,Class<? extends AccountTradeHandler>  accountTradeHandler,Class<? extends AccountOperateRule> operationRule)
	{
		this.operationName = operationName;
		this.accountTradeHandler = accountTradeHandler;
		this.operationRule = operationRule;
	}

	

	public Class<? extends AccountTradeHandler>  getAccountTradeHandler() {
		return accountTradeHandler;
	}



	public String getOperationName()
	{
		return operationName;
	}



	@Override
	public String getAccountTradeType()
	{
		return this.name();
	}



	@Override
	public Class<? extends AccountOperateRule> getOperationRule()
	{
		
		return operationRule;
	}
	
	
	

	
}
