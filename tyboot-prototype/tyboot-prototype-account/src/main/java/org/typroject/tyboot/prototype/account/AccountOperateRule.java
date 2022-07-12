package org.typroject.tyboot.prototype.account;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountOperateRule.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountOperateRule.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class AccountOperateRule {

	
	/**
	 * 检查当前操作是否可以进行
	 * @return
	 */
	public abstract boolean  checkOperation(Account account);



	protected boolean checkAccountStatus(Account account){
		//TODO 校验账户是否被锁定 以及是否失效
		if(AccountStatus.INVALID.name().equals(account.getAccountInfoModel().getAccountStatus())){
			throw new AccountTradeException("账户已失效.");
		}

		if(AccountStatus.LOCKED.name().equals(account.getAccountInfoModel().getAccountStatus())){
			throw new AccountTradeException("账户已被锁定.");
		}
		return true;
	}


} 
