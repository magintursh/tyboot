package org.typroject.tyboot.face.account.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户虚拟账户表，记录所有公网用户的虚拟账户，account_no字段预留，用以多账户支持 model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
public class AccountInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

                /**
         * agencycode
         */
    private String agencyCode;
                /**
         * 账户编号
         */
    private String accountNo;
                /**
         * 账户余额
         */
    private BigDecimal balance;
                /**
         * 账户类型（用户虚拟账户，优惠额度账户、冻结资金账户）
         */
    private String accountType;
                /**
         * 账户状态(正常\冻结\资金冻结\失效)
         */
    private String accountStatus;
                /**
         * 支付密码（md5加密）
         */
    private String paymentPassword;
                /**
         * 累计充值总额(只包含主动从外部账户入账的金额)
         */
    private BigDecimal cumulativeBalance;
                /**
         * 数据版本
         */
    private Long updateVersion;
        private Date createTime;

        private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgencyCode() {
return agencyCode;
}

    public void setAgencyCode(String agencyCode) {
this.agencyCode = agencyCode;
}

public String getAccountNo() {
return accountNo;
}

    public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public BigDecimal getBalance() {
return balance;
}

    public void setBalance(BigDecimal balance) {
this.balance = balance;
}

public String getAccountType() {
return accountType;
}

    public void setAccountType(String accountType) {
this.accountType = accountType;
}

public String getAccountStatus() {
return accountStatus;
}

    public void setAccountStatus(String accountStatus) {
this.accountStatus = accountStatus;
}

public String getPaymentPassword() {
return paymentPassword;
}

    public void setPaymentPassword(String paymentPassword) {
this.paymentPassword = paymentPassword;
}

public BigDecimal getCumulativeBalance() {
return cumulativeBalance;
}

    public void setCumulativeBalance(BigDecimal cumulativeBalance) {
this.cumulativeBalance = cumulativeBalance;
}

public Long getUpdateVersion() {
return updateVersion;
}

    public void setUpdateVersion(Long updateVersion) {
this.updateVersion = updateVersion;
}

public Date getCreateTime() {
return createTime;
}

    public void setCreateTime(Date createTime) {
this.createTime = createTime;
}
	
}
