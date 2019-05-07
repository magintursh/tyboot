package org.typroject.tyboot.face.account.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 账户转账记录:
特指：内部账户之间主动转账，冻结/解冻资金所引发的转账到冻结账户，内部账户到外部账户的主动转账
 * </p>
 *
 * @author 子杨
 * @since 2018-01-24
 */
@TableName("account_transfer_record")
public class AccountTransferRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
	@TableField("BILL_NO")
	private String billNo;
    /**
     * 来源账户编号
     */
	@TableField("SOURCE_ACCOUNT_NO")
	private String sourceAccountNo;
    /**
     * 目标账户编号
     */
	@TableField("TARGET_ACCOUNT_NO")
	private String targetAccountNo;
    /**
     * 转账类型(内部账户间主动转账，冻结/解冻)
     */
	@TableField("TRANSFER_TYPE")
	private String transferType;
    /**
     * 转账金额
     */
	@TableField("TRANSFER_AMOUNT")
	private BigDecimal transferAmount;
    /**
     * 转账状态(转账中，转账成功、转账失败)
     */
	@TableField("TRANSFER_STATUS")
	private String transferStatus;
    /**
     * 转账时间
     */
	@TableField("TRANSFER_TIME")
	private Date transferTime;
    /**
     * 转账附言
     */
	@TableField("TRANSFER_POSTSCRIPT")
	private String transferPostscript;
    /**
     * 账户类型（用户虚拟账户，优惠额度账户、冻结资金账户）
     */
	@TableField("SOURCE_ACCOUNT_TYPE")
	private String sourceAccountType;
    /**
     * 账户类型（用户虚拟账户，优惠额度账户、冻结资金账户）
     */
	@TableField("TARGET_ACCOUNT_TYPE")
	private String targetAccountType;

	@TableField("USER_ID")
	private String userId;

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

	public String getSourceAccountNo() {
		return sourceAccountNo;
	}

	public void setSourceAccountNo(String sourceAccountNo) {
		this.sourceAccountNo = sourceAccountNo;
	}

	public String getTargetAccountNo() {
		return targetAccountNo;
	}

	public void setTargetAccountNo(String targetAccountNo) {
		this.targetAccountNo = targetAccountNo;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public String getTransferPostscript() {
		return transferPostscript;
	}

	public void setTransferPostscript(String transferPostscript) {
		this.transferPostscript = transferPostscript;
	}

	public String getSourceAccountType() {
		return sourceAccountType;
	}

	public void setSourceAccountType(String sourceAccountType) {
		this.sourceAccountType = sourceAccountType;
	}

	public String getTargetAccountType() {
		return targetAccountType;
	}

	public void setTargetAccountType(String targetAccountType) {
		this.targetAccountType = targetAccountType;
	}

}
