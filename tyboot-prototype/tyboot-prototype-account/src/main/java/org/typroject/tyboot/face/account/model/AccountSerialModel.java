package org.typroject.tyboot.face.account.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 虚拟账户金额变更记录表，所有针对账户金额的变动操作都要记录到此表中， model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
public class AccountSerialModel extends BaseModel {

    private static final long serialVersionUID = 1L;

                /**
         * 账户编号(预留字段，暂不启用)
         */
    private String accountNo;
                /**
         * 起始余额
         */
    private BigDecimal initialPrefundedBalance;
                /**
         * 变更金额
         */
    private BigDecimal changeAmount;
                /**
         * 最终余额
         */
    private BigDecimal finalBalance;
                /**
         * 操作类型（充值recharge，消费consumption(消费类型还可以再分)，提现cash-out）
         */
    private String operationType;
                /**
         * 操作时间
         */
    private Date operateTime;
                /**
         * 交易账单编号
         */
    private String billNo;
                /**
         * 账户信息的数据版本
         */
    private Long updateVersion;
                /**
         * 账户类型（用户虚拟账户，优惠额度账户、冻结资金账户）
         */
    private String accountType;
                /**
         * 记账类型
         */
    private String bookkeeping;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
return accountNo;
}

    public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public BigDecimal getInitialPrefundedBalance() {
return initialPrefundedBalance;
}

    public void setInitialPrefundedBalance(BigDecimal initialPrefundedBalance) {
this.initialPrefundedBalance = initialPrefundedBalance;
}

public BigDecimal getChangeAmount() {
return changeAmount;
}

    public void setChangeAmount(BigDecimal changeAmount) {
this.changeAmount = changeAmount;
}

public BigDecimal getFinalBalance() {
return finalBalance;
}

    public void setFinalBalance(BigDecimal finalBalance) {
this.finalBalance = finalBalance;
}

public String getOperationType() {
return operationType;
}

    public void setOperationType(String operationType) {
this.operationType = operationType;
}

public Date getOperateTime() {
return operateTime;
}

    public void setOperateTime(Date operateTime) {
this.operateTime = operateTime;
}

public String getBillNo() {
return billNo;
}

    public void setBillNo(String billNo) {
this.billNo = billNo;
}

public Long getUpdateVersion() {
return updateVersion;
}

    public void setUpdateVersion(Long updateVersion) {
this.updateVersion = updateVersion;
}

public String getAccountType() {
return accountType;
}

    public void setAccountType(String accountType) {
this.accountType = accountType;
}

public String getBookkeeping() {
return bookkeeping;
}

    public void setBookkeeping(String bookkeeping) {
this.bookkeeping = bookkeeping;
}
	
}
