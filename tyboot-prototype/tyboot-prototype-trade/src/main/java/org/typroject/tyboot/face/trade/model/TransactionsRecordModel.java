package org.typroject.tyboot.face.trade.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易记录表 model
 * </p>
 *
 * @author 子杨
 * @since 2017-08-31
 */
public class TransactionsRecordModel extends BaseModel {

    private static final long serialVersionUID = 1L;

                                /**
             * 账单编号
             */
            private String billNo;
                                /**
             * 本地交易流水号
             */
            private String serialNo;
                                /**
             * 交易金额
             */
            private BigDecimal tradeAmount;
                                /**
             * 商家编码
             */
            private String agencyCode;
                                /**
             * 支付完成时间(前端返回交易完成更新此时间)
             */
            private Date finishedTime;
                                /**
             * 异步通知时间
             */
            private Date asyncFinishTime;
                                /**
             * 交易类型：payment:支付(用户支付订单金额到系统)
             */
            private String tradeType;

    /**
     * 交易类型：支付商品，商家结算，红包，虚拟账户充值
     */
    private String billType;
                                /**
             * 使用的支付方式(支付宝，微信)
             */
            private String payMethod;


            private String userId;


    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
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
                    
        public String getSerialNo() {
        return serialNo;
    }

                    public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }
                    
        public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

                    public void setTradeAmount(BigDecimal tradeAmount) {
                this.tradeAmount = tradeAmount;
            }
                    
        public String getAgencyCode() {
        return agencyCode;
    }

                    public void setAgencyCode(String agencyCode) {
                this.agencyCode = agencyCode;
            }
                    
        public Date getFinishedTime() {
        return finishedTime;
    }

                    public void setFinishedTime(Date finishedTime) {
                this.finishedTime = finishedTime;
            }
                    
        public Date getAsyncFinishTime() {
        return asyncFinishTime;
    }

                    public void setAsyncFinishTime(Date asyncFinishTime) {
                this.asyncFinishTime = asyncFinishTime;
            }
                    
        public String getTradeType() {
        return tradeType;
    }

                    public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }
                    
        public String getPayMethod() {
        return payMethod;
    }

                    public void setPayMethod(String payMethod) {
                this.payMethod = payMethod;
            }
    	
}
