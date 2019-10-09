package org.typroject.tyboot.face.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;

/**
 * <p>
 * 订单产品关系表 model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderProductRelationModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 商品信息主键
     */
    private Long productSeq;
    /**
     * 商品信息实体类型
     */
    private String productType;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 商品当前单价
     */
    private BigDecimal productPrice;


}
