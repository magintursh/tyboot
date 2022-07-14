package org.typroject.tyboot.prototype.order;

import org.typroject.tyboot.core.foundation.exception.BaseException;

/**
 * Created by magintursh on 2018/3/14.
 */
public class OrderException extends BaseException {

    public OrderException(String message)
    {
        super(message, "OrderException", "订单处理异常.");
        this.httpStatus = 400;
    }
}
