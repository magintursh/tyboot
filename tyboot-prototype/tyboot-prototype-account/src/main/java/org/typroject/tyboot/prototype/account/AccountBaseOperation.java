package org.typroject.tyboot.prototype.account;

import org.typroject.tyboot.prototype.account.rule.AccountIncomeRule;
import org.typroject.tyboot.prototype.account.rule.AccountSpendRule;
import org.typroject.tyboot.prototype.account.trade.AccountTradeHandler;
import org.typroject.tyboot.prototype.account.trade.AccountTradeType;

public enum AccountBaseOperation implements AccountTradeType
{

	/**
	 * 入账
	 */
	INCOME("入账", AccountIncomeRule.class),
	
	/**
	 * 出账
	 */
	SPEND("出账", AccountSpendRule.class),


	FREEZE("冻结",AccountSpendRule.class),

	UNFREEZE("解冻",AccountIncomeRule.class),

	/**
	 * 临时冻结的金额已经处理成功之后，将其释放掉，
	 */
	RELEASE_FROZEN("释放冻结金额",AccountIncomeRule.class),

	/**
	 * 初始化账户
	 */
	INIT("初始化",null),
	
	/**
	 * 锁定账户
	 */
	LOCK("锁定",null),
	
	/**
	 * 解锁账户
	 */
	UNLOCK("解锁",null),
	
	
	/**
	 * 失效
	 */
	INVALID("失效",null);
	
	private String operationName;
	private Class<? extends AccountOperateRule> operationRule;
	
	
	private AccountBaseOperation( String operationName,Class<? extends AccountOperateRule> operationRule)
	{
		this.operationName = operationName;
		this.operationRule = operationRule;
	}
	
	
	
	public String getOperationName()
	{
		return operationName;
	}
	public Class<? extends AccountOperateRule> getOperationRule()
	{
		return operationRule;
	}

	public String getAccountTradeType()
	{
		return this.name();
	}



	@Override
	public Class<? extends AccountTradeHandler>  getAccountTradeHandler()
	{
		return null;
	}
	
	
}
