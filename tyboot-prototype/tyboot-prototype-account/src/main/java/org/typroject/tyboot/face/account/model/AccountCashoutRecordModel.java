package org.typroject.tyboot.face.account.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 提现申请表 model
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
public class AccountCashoutRecordModel extends BaseModel {

    private static final long serialVersionUID = 1L;

                /**
         * 用户名称
         */
    private String userName;
                /**
         * 申请编号
         */
    private String applayNo;
                /**
         * 申请类型（支付宝|银行）
         */
    private String applayType;
                /**
         * 转账账号
         */
    private String transferAccount;
                /**
         * 转账用户名称
         */
    private String transferName;
                /**
         * 开户行
         */
    private String openBank;
                /**
         * 提现金额
         */
    private BigDecimal outAmount;
                /**
         * 申请状态（申请中pending|已确认confirmed|自动确认confirmed_auto|拒绝refuse）
         */
    private String applyStatus;
                /**
         * 提现完成时间
         */
    private Date finishTime;
                /**
         * 手续费
         */
    private BigDecimal poundage;
                /**
         * 申请提现金额
         */
    private BigDecimal applayAmount;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
return userName;
}

    public void setUserName(String userName) {
this.userName = userName;
}

public String getApplayNo() {
return applayNo;
}

    public void setApplayNo(String applayNo) {
this.applayNo = applayNo;
}

public String getApplayType() {
return applayType;
}

    public void setApplayType(String applayType) {
this.applayType = applayType;
}

public String getTransferAccount() {
return transferAccount;
}

    public void setTransferAccount(String transferAccount) {
this.transferAccount = transferAccount;
}

public String getTransferName() {
return transferName;
}

    public void setTransferName(String transferName) {
this.transferName = transferName;
}

public String getOpenBank() {
return openBank;
}

    public void setOpenBank(String openBank) {
this.openBank = openBank;
}

public BigDecimal getOutAmount() {
return outAmount;
}

    public void setOutAmount(BigDecimal outAmount) {
this.outAmount = outAmount;
}

public String getApplyStatus() {
return applyStatus;
}

    public void setApplyStatus(String applyStatus) {
this.applyStatus = applyStatus;
}

public Date getFinishTime() {
return finishTime;
}

    public void setFinishTime(Date finishTime) {
this.finishTime = finishTime;
}

public BigDecimal getPoundage() {
return poundage;
}

    public void setPoundage(BigDecimal poundage) {
this.poundage = poundage;
}

public BigDecimal getApplayAmount() {
return applayAmount;
}

    public void setApplayAmount(BigDecimal applayAmount) {
this.applayAmount = applayAmount;
}
	
}
