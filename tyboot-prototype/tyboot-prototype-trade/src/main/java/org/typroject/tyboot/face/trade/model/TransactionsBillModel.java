package org.typroject.tyboot.face.trade.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易账单表 model
 * </p>
 *
 * @author 子杨
 * @since 2017-08-31
 */
public class TransactionsBillModel extends BaseModel {

    private static final long serialVersionUID = 15615648132L;

                                /**
             * 账单编号L
             */
            private String billNo;
                                /**
             * 账单总金额
             */
            private BigDecimal amount;
                                /**
             * 交易类型：支付商品，商家结算，红包，虚拟账户充值
             */
            private String billType;
                                /**
             * 账单状态；等待结账，已结账
             */
            private String billStatus;
                                /**
             * 生成时间
             */
            private Date createTime;
                                /**
             * 结账时间
             */
            private Date checkoutTime;
                        private Date refundTime;
                                /**
             * 商家编码
             */
            private String agencyCode;
                                /**
             * 是否有退款(如果有退款，则需要根据账单号在退款记录表中查看具体的退款信息）
             */
            private String refund;


    private String userId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                    
        public String getBillType() {
        return billType;
    }

                    public void setBillType(String billType) {
                this.billType = billType;
            }
                    
        public String getBillStatus() {
        return billStatus;
    }

                    public void setBillStatus(String billStatus) {
                this.billStatus = billStatus;
            }
                    
        public Date getCreateTime() {
        return createTime;
    }

                    public void setCreateTime(Date createTime) {
                this.createTime = createTime;
            }
                    
        public Date getCheckoutTime() {
        return checkoutTime;
    }

                    public void setCheckoutTime(Date checkoutTime) {
                this.checkoutTime = checkoutTime;
            }
                    
        public Date getRefundTime() {
        return refundTime;
    }

                    public void setRefundTime(Date refundTime) {
                this.refundTime = refundTime;
            }
                    
        public String getAgencyCode() {
        return agencyCode;
    }

                    public void setAgencyCode(String agencyCode) {
                this.agencyCode = agencyCode;
            }
                    
        public String getRefund() {
        return refund;
    }

                    public void setRefund(String refund) {
                this.refund = refund;
            }
    	
}
