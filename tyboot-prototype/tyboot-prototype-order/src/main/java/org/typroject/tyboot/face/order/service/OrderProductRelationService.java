package org.typroject.tyboot.face.order.service;


import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.order.model.OrderProductRelationModel;
import org.typroject.tyboot.face.order.orm.dao.OrderProductRelationMapper;
import org.typroject.tyboot.face.order.orm.entity.OrderProductRelation;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 订单产品关系表 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-01-14
 */
@Component
public class OrderProductRelationService extends BaseService<OrderProductRelationModel, OrderProductRelation, OrderProductRelationMapper>
{





        public OrderProductRelationModel createRelation(String orderSn, Long productSeq, String productType, String productName, Integer count, BigDecimal productPrice) throws Exception
        {
            OrderProductRelationModel relationModel = new OrderProductRelationModel();
            relationModel.setCount(count);
            relationModel.setOrderSn(orderSn);
            relationModel.setProductName(productName);
            relationModel.setProductPrice(productPrice);
            relationModel.setProductSeq(productSeq);
            relationModel.setProductType(productType);
            return this.createWithModel(relationModel);
        }


      public List<OrderProductRelationModel> queryByOrderSn(String orderSn)throws Exception
      {
          return this.queryForList("REC_DATE", false,orderSn);
      }



    public OrderProductRelationModel queryFirstByOrderSn(String orderSn)throws Exception
    {
        OrderProductRelationModel returnModel = null;
        List<OrderProductRelationModel> models = this.queryForList("REC_DATE", false,orderSn);

        if(!ValidationUtil.isEmpty(models))
            returnModel = models.get(0);

        return returnModel;
    }






}
