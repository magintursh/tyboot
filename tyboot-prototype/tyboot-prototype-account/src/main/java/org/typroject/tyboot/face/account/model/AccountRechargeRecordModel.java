package org.typroject.tyboot.face.account.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 充值记录表，只记录交易成功的充值信息 model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
public class AccountRechargeRecordModel extends BaseModel {

    private static final long serialVersionUID = 1L;


    /**
     * 用户编号
     */
    private String userId;
                /**
         * 充值账户编号
         */
    private String accountNo;
                /**
         *  充值时间
         */
    private Date rechargeTime;
                /**
         * 充值金额
         */
    private BigDecimal rechargeAmount;
                /**
         * 充值账单编号
         */
    private String billNo;
                /**
         * 账户类型（用户虚拟账户，优惠额度账户、冻结资金账户）
         */
    private String accountType;


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

public Date getRechargeTime() {
return rechargeTime;
}

    public void setRechargeTime(Date rechargeTime) {
this.rechargeTime = rechargeTime;
}

public BigDecimal getRechargeAmount() {
return rechargeAmount;
}

    public void setRechargeAmount(BigDecimal rechargeAmount) {
this.rechargeAmount = rechargeAmount;
}

public String getBillNo() {
return billNo;
}

    public void setBillNo(String billNo) {
this.billNo = billNo;
}

public String getAccountType() {
return accountType;
}

    public void setAccountType(String accountType) {
this.accountType = accountType;
}
	
}
