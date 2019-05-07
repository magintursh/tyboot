package org.typroject.tyboot.face.trade.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易流水表 model
 * </p>
 *
 * @author 子杨
 * @since 2017-08-31
 */
public class TransactionsSerialModel extends BaseModel {

    private static final long serialVersionUID = 1L;

                                /**
             * 本地交易流水号（25位）
             */
            private String serialNo;
                                /**
             * 账单编号
             */
            private String billNo;
                                /**
             * 商家编码
             */
            private String agencyCode;
                                /**
             * 支付渠道返回的流水号
             */
            private String channelSerialNo;
                                /**
             * 交易金额
             */
            private BigDecimal tradeAmount;
                                /**
             * 减免金额
             */
            private BigDecimal discntFee;
                                /**
             * 减免备注说明
             */
            private String discntDesc;
                                /**
             * 即时返回时间
             */
            private Date syncFinishTime;
                                /**
             * 系统向支付平台pingxx发起请求时间
             */
            private Date sendTime;
                                /**
             * 支付结果备注信息(支付渠道，pingxx支付平台都有可能返回结果信息,)
             */
            private String resultMessage;
                                /**
             * 支付完成时间(前端返回交易完成更新此时间)
             */
            private Date finishTime;
                                /**
             * 异步通知时间
             */
            private Date asyncFinishTime;
                                /**
             * 发起支付的终端ip
             */
            private String clientIp;
                                /**
             * 支付终端设备平台类型
             */
            private String clientPlatform;
                                /**
             * 使用的支付方式(支付宝，微信)
             */
            private String payMethod;
                                /**
             * 交易状态：支付申请;支付提交;已返回凭证;前端返回(成功，失败),pingxx异步返回(成功，失败)交易过期，交易被取消
             */
            private String tradeStatus;
                                /**
             * 交易类型：支付商品，商家结算，红包，虚拟账户充值
             */
            private String tradeType;

    private String billType;


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
    


                    
        public String getSerialNo() {
        return serialNo;
    }

                    public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }
                    
        public String getBillNo() {
        return billNo;
    }

                    public void setBillNo(String billNo) {
                this.billNo = billNo;
            }
                    
        public String getAgencyCode() {
        return agencyCode;
    }

                    public void setAgencyCode(String agencyCode) {
                this.agencyCode = agencyCode;
            }
                    
        public String getChannelSerialNo() {
        return channelSerialNo;
    }

                    public void setChannelSerialNo(String channelSerialNo) {
                this.channelSerialNo = channelSerialNo;
            }
                    
        public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

                    public void setTradeAmount(BigDecimal tradeAmount) {
                this.tradeAmount = tradeAmount;
            }
                    
        public BigDecimal getDiscntFee() {
        return discntFee;
    }

                    public void setDiscntFee(BigDecimal discntFee) {
                this.discntFee = discntFee;
            }
                    
        public String getDiscntDesc() {
        return discntDesc;
    }

                    public void setDiscntDesc(String discntDesc) {
                this.discntDesc = discntDesc;
            }
                    
        public Date getSyncFinishTime() {
        return syncFinishTime;
    }

                    public void setSyncFinishTime(Date syncFinishTime) {
                this.syncFinishTime = syncFinishTime;
            }
                    
        public Date getSendTime() {
        return sendTime;
    }

                    public void setSendTime(Date sendTime) {
                this.sendTime = sendTime;
            }
                    
        public String getResultMessage() {
        return resultMessage;
    }

                    public void setResultMessage(String resultMessage) {
                this.resultMessage = resultMessage;
            }
                    
        public Date getFinishTime() {
        return finishTime;
    }

                    public void setFinishTime(Date finishTime) {
                this.finishTime = finishTime;
            }
                    
        public Date getAsyncFinishTime() {
        return asyncFinishTime;
    }

                    public void setAsyncFinishTime(Date asyncFinishTime) {
                this.asyncFinishTime = asyncFinishTime;
            }
                    
        public String getClientIp() {
        return clientIp;
    }

                    public void setClientIp(String clientIp) {
                this.clientIp = clientIp;
            }
                    
        public String getClientPlatform() {
        return clientPlatform;
    }

                    public void setClientPlatform(String clientPlatform) {
                this.clientPlatform = clientPlatform;
            }
                    
        public String getPayMethod() {
        return payMethod;
    }

                    public void setPayMethod(String payMethod) {
                this.payMethod = payMethod;
            }
                    
        public String getTradeStatus() {
        return tradeStatus;
    }

                    public void setTradeStatus(String tradeStatus) {
                this.tradeStatus = tradeStatus;
            }
                    
        public String getTradeType() {
        return tradeType;
    }

                    public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }
    	
}
