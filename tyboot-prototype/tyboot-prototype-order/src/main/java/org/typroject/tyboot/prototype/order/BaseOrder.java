package org.typroject.tyboot.prototype.order;

import lombok.Data;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
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


    public static BaseOrder getInstance(BaseOrderInfo baseOrderInfo, OrderStatus[] orderStatus) {

        boolean correctStatus = false;
        OrderStatus currentStatus = null;
        for (OrderStatus status : orderStatus) {
            if (baseOrderInfo.getOrderStatus().equals(status.getStatusCode())) {
                currentStatus = status;
            }
            correctStatus = correctStatus || baseOrderInfo.getOrderStatus().equals(status.getStatusCode());
        }
        if (!correctStatus || ValidationUtil.isEmpty(currentStatus)) {
            throw new OrderException("订单状态有误");
        }

        BaseOrder baseOrder = new BaseOrder();
        baseOrder.setOrderSn(baseOrderInfo.getOrderSn());
        baseOrder.setBaseOrderInfo(baseOrderInfo);
        baseOrder.setOrderStatus(currentStatus);
        baseOrder.setBaseOrderInfo(baseOrderInfo);
        return baseOrder;
    }
}
