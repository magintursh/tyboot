package org.typroject.tyboot.face.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单主表，订单流转所需的主要字段。
 * 在订单流转结束之后将被转移到订单历史表，当前表只保存流转中的订单信息 model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 订单的唯一编号
     */
    private String orderSn;
    /**
     * 账单编号
     */
    private String billNo;
    /**
     * 订单总金额
     */
    private BigDecimal amount;
    /**
     * 商品总价
     */
    private BigDecimal productAmount;
    /**
     * 优惠抵扣金额
     */
    private Integer couponDeduction;
    /**
     * 订单类型（不同的订单类型可能会有不同的主线流程，慎重定义）
     */
    private String orderType;
    /**
     * 订单状态（订单生命周期内的所有状态标识）
     */
    private String orderStatus;
    /**
     * 订单生成时间
     */
    private Date createTime;
    /**
     * 订单支付时间
     */
    private Date payTime;
    /**
     * 支付方式（在交易模块定义)
     */
    private String payMethod;
    /**
     * 支付状态（0未支付,1已支付）
     */
    private String payStatus;
    /**
     * 机构编码
     */
    private String agencyCode;
    /**
     * 订单来源（PUBLIC:公网, AGENCY:商家）
     */
    private String source;


    private String userId;


    private String comment;

    private String remark;


    private String nickName;
    private String mobile;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public Integer getCouponDeduction() {
        return couponDeduction;
    }

    public void setCouponDeduction(Integer couponDeduction) {
        this.couponDeduction = couponDeduction;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
