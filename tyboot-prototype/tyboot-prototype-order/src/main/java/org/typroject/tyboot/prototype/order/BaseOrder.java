package org.typroject.tyboot.prototype.order;

import org.typroject.tyboot.prototype.order.state.OrderStatus;
import org.typroject.tyboot.face.order.model.OrderInfoModel;

import java.util.Map;


public  class BaseOrder{





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
	protected OrderInfoModel orderInfoModel;

	/**
	 * 订单交易信息
	 */
	protected Map<String,Object> tradeParams;


	public BaseOrder() {
	}

	public BaseOrder(String orderSn, OrderStatus orderStatus, OrderInfoModel orderInfoModel) {
		this.orderSn = orderSn;
		this.orderStatus = orderStatus;
		this.orderInfoModel = orderInfoModel;
	}

	public String getOrderSn() {
		return orderSn;
	}


	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}



	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	public OrderInfoModel getOrderInfoModel(){
		return orderInfoModel;
	}


	public void setOrderInfoModel(OrderInfoModel orderInfoModel)
	{
		this.orderInfoModel = orderInfoModel;
	}


	public Map<String, Object> getTradeParams(){
		return tradeParams;
	}


	public void setTradeParams(Map<String, Object> tradeParams){
		this.tradeParams = tradeParams;
	}
}
