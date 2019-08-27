package org.typroject.tyboot.face.order.model;

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


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(Long productSeq) {
        this.productSeq = productSeq;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

}
