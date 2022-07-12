package org.typroject.tyboot.prototype.order.state;

import org.typroject.tyboot.prototype.order.rule.OperationLimitHandler;

/**
 * 
 * <pre>
 * 
 *  File: OrderStatus.java
 * 
 *  Description:
 *  订单状态顶级接口
 * 
 *  Notes:
 *  OrderStatus.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public interface OrderStatus {

	
	/**
	 * 
	 * state实现类Bean名称
	 */
	Class<? extends StateHandler> getStateHandler();
	

	/**
	 * 
	 * 订单限制条件Bean名称
	 *
	 * @return
	 */
	Class<? extends OperationLimitHandler> getRuleHandler();
	
	
	/**
	 * 
	 * 解析名称（一般未状态的中文名称）
	 *
	 * @return
	 */
	String getStateName();
	
	
	
}
