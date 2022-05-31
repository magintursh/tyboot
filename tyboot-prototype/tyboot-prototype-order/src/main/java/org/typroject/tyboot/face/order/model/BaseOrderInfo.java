package org.typroject.tyboot.face.order.model;

import java.math.BigDecimal;
import java.util.Date;


public interface BaseOrderInfo {


    String getOrderSn();

    String getBillNo();

    BigDecimal getAmount();

    String getOrderType();

    String getOrderStatus();

    Date getCreateTime();

    String getUserId();
}
