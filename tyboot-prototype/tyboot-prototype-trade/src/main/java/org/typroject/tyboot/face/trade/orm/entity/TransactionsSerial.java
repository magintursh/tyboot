package org.typroject.tyboot.face.trade.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易流水表
 * </p>
 *
 * @author 子杨
 * @since 2017-08-31
 */
@TableName("trade_transactions_serial")
public class TransactionsSerial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 本地交易流水号（25位）
     */
	@TableField("SERIAL_NO")
	private String serialNo;
    /**
     * 账单编号
     */
	@TableField("BILL_NO")
	private String billNo;
    /**
     * 商家编码
     */
	@TableField("AGENCY_CODE")
	private String agencyCode;
    /**
     * 支付渠道返回的流水号
     */
	@TableField("CHANNEL_SERIAL_NO")
	private String channelSerialNo;
    /**
     * 交易金额
     */
	@TableField("TRADE_AMOUNT")
	private BigDecimal tradeAmount;
    /**
     * 减免金额
     */
	@TableField("DISCNT_FEE")
	private Integer discntFee;
    /**
     * 减免备注说明
     */
	@TableField("DISCNT_DESC")
	private String discntDesc;
    /**
     * 即时返回时间
     */
	@TableField("SYNC_FINISH_TIME")
	private Date syncFinishTime;
    /**
     * 系统向支付平台pingxx发起请求时间
     */
	@TableField("SEND_TIME")
	private Date sendTime;
    /**
     * 支付结果备注信息(支付渠道，pingxx支付平台都有可能返回结果信息,)
     */
	@TableField("RESULT_MESSAGE")
	private String resultMessage;
    /**
     * 支付完成时间(前端返回交易完成更新此时间)
     */
	@TableField("FINISH_TIME")
	private Date finishTime;
    /**
     * 异步通知时间
     */
	@TableField("ASYNC_FINISH_TIME")
	private Date asyncFinishTime;
    /**
     * 发起支付的终端ip
     */
	@TableField("CLIENT_IP")
	private String clientIp;
    /**
     * 支付终端设备平台类型
     */
	@TableField("CLIENT_PLATFORM")
	private String clientPlatform;
    /**
     * 使用的支付方式(支付宝，微信)
     */
	@TableField("PAY_METHOD")
	private String payMethod;
    /**
     * 交易状态：支付申请;支付提交;已返回凭证;前端返回(成功，失败),pingxx异步返回(成功，失败)交易过期，交易被取消
     */
	@TableField("TRADE_STATUS")
	private String tradeStatus;
    /**
     * 交易类型：支付商品，商家结算，红包，虚拟账户充值
     */
	@TableField("TRADE_TYPE")
	private String tradeType;

	/**
	 * 交易类型：支付商品，商家结算，红包，虚拟账户充值
	 */
	@TableField("BILL_TYPE")
	private String billType;


	@TableField("USER_ID")
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

	public Integer getDiscntFee() {
		return discntFee;
	}

	public void setDiscntFee(Integer discntFee) {
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
