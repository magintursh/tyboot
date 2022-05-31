package org.typroject.tyboot.prototype.order;

import lombok.Data;
import org.typroject.tyboot.face.order.model.BaseOrderInfo;
import org.typroject.tyboot.prototype.order.state.OrderStatus;

import java.util.Map;


@Data
public class BaseOrder {

    /**
     * 订单编号（全局唯一）
     */
    protected String orderSn;

    /**
     * 订单状态
     */
    protected OrderStatus orderStatus;

    /**
     * 订单实体
     */
    protected BaseOrderInfo baseOrderInfo;

    /**
     * 订单交易信息
     */
    protected Map<String, Object> tradeParams;
}
