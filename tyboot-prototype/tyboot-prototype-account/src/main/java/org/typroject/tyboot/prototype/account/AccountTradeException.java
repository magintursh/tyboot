package org.typroject.tyboot.prototype.account;

import org.typroject.tyboot.core.foundation.exception.BaseException;

/**
 * description: AccountTradeException <br>
 * date: 2022/7/11 23:39 <br>
 * author: ziyang <br>
 * version: 1.0 <br>
 */
public class AccountTradeException extends BaseException {
    public AccountTradeException(String message)
    {
        super(message,AccountTradeException.class.getSimpleName(),"虚拟账户交易出错.");
        this.httpStatus = 400;
    }
}
