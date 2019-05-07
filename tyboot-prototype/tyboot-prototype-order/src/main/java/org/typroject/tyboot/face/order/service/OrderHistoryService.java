package org.typroject.tyboot.face.order.service;


import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.order.model.OrderHistoryModel;
import org.typroject.tyboot.face.order.orm.dao.OrderHistoryMapper;
import org.typroject.tyboot.face.order.orm.entity.OrderHistory;


/**
 * <p>
 * 订单归档表 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-01-14
 */
@Component
public class OrderHistoryService extends BaseService<OrderHistoryModel, OrderHistory, OrderHistoryMapper>
{

}
