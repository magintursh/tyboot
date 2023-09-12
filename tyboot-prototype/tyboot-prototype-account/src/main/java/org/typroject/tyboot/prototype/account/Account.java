package org.typroject.tyboot.prototype.account;


import com.baomidou.mybatisplus.core.toolkit.Sequence;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.face.account.model.AccountInfoModel;
import org.typroject.tyboot.face.account.model.AccountSerialModel;
import org.typroject.tyboot.face.account.service.AccountFrozenSerialService;
import org.typroject.tyboot.face.account.service.AccountInfoService;
import org.typroject.tyboot.face.account.service.AccountSerialService;
import org.typroject.tyboot.prototype.account.trade.AccountTradeType;

import java.math.BigDecimal;


/**
 * <pre>
 *  Tyrest
 *  File: Account.java
 *
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 *
 *  Description:
 *  虚拟账户操作类
 *  定义虚拟账户所有的操作
 *  所有方法不可在子类重写
 *
 *  Notes:
 *  $Id: Account.java  Tyrest\magintrursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class Account {


    private static AccountInfoService accountInfoService;
    private static AccountSerialService accountSerialService;
    private static AccountFrozenSerialService accountFrozenSerialService;
    private static Sequence sequence;

    static {
        accountInfoService = (AccountInfoService) SpringContextHelper.getBean(AccountInfoService.class);
        accountSerialService = (AccountSerialService) SpringContextHelper.getBean(AccountSerialService.class);
        accountFrozenSerialService = (AccountFrozenSerialService) SpringContextHelper.getBean(AccountFrozenSerialService.class);
        sequence =  SpringContextHelper.getBean(Sequence.class);
    }

    private Account() {

    }

    /**
     * 账户对象
     */
    private AccountInfoModel accountInfoModel;


    /**
     * 初始化账户
     *
     * @throws Exception
     */
    protected static AccountInfoModel initAccountInfo(String userId,String agencyCode, AccountType accountType) {
        return accountInfoService.initAccountInfo(userId, accountType, createAccountNo(userId, accountType),agencyCode);
    }

    /**
     * 入账
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public final boolean income(BigDecimal amount, AccountTradeType accountTradeType, String billNo, String postscript) {
        return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.INCOME, postscript,null);
    }


    /**
     * 出账
     *
     * @param amount           出账
     * @param accountTradeType 前置交易类型
     * @param billNo           账单号
     * @return
     * @throws Exception
     */
    public final boolean spend(BigDecimal amount, AccountTradeType accountTradeType, String billNo, String postscript) {
        return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.SPEND, postscript,null);
    }


    /**
     * 冻结
     *
     * @param amount 金额
     * @param billNo 账单号
     * @return
     * @throws Exception
     */
    public final boolean frozen(BigDecimal amount, AccountTradeType accountTradeType,String billNo, String postscript) {
        Long newFrozenUpdateVersion = sequence.nextId();
        accountFrozenSerialService.createAccountFrozenSerial(this.accountInfoModel.getUserId(),
                this.getAccountInfoModel().getAgencyCode(),
                this.accountInfoModel.getAccountNo(),
                this.accountInfoModel.getAccountType(),
                this.accountInfoModel.getFrozenUpdateVersion(),
                newFrozenUpdateVersion,
                billNo, amount,
                accountTradeType,
                AccountBaseOperation.FREEZE, postscript);
        return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.FREEZE, postscript,newFrozenUpdateVersion);
    }

    /**
     * 从冻结余额中释放指定的金额
     *
     * @param amount
     * @return
     */
    public final boolean releaseFrozen(BigDecimal amount, AccountTradeType accountTradeType,String billNo, String postscript) {
        boolean returnFlag = false;
        Long newFrozenUpdateVersion = sequence.nextId();
        accountFrozenSerialService.createAccountFrozenSerial(this.accountInfoModel.getUserId(),
                this.getAccountInfoModel().getAgencyCode(),
                this.accountInfoModel.getAccountNo(),
                this.accountInfoModel.getAccountType(),
                this.accountInfoModel.getFrozenUpdateVersion(),
                newFrozenUpdateVersion,
                billNo, amount,
                accountTradeType,
                AccountBaseOperation.RELEASE_FROZEN, postscript);


        AccountInfoModel updateResult
                = accountInfoService.updateFinalBalance(this.accountInfoModel.getAccountNo(),
                amount,
                null,
                this.accountInfoModel.getUpdateVersion(),
                null,
                AccountBaseOperation.RELEASE_FROZEN,this.accountInfoModel.getFrozenUpdateVersion(),newFrozenUpdateVersion);


        if (!ValidationUtil.isEmpty(updateResult)) {
            returnFlag = true;
            //#3.刷新账户信息
            this.refresh(this.accountInfoModel.getAccountNo());
        }
        return returnFlag;
    }


    /**
     * 冻结
     *
     * @param amount 金额
     * @param billNo 账单号
     * @return
     * @throws Exception
     */
    public final boolean unFrozen(BigDecimal amount,AccountTradeType accountTradeType, String billNo, String postscript) {
        Long newUpdateVersion = sequence.nextId();
        accountFrozenSerialService.createAccountFrozenSerial(this.accountInfoModel.getUserId(),
                this.getAccountInfoModel().getAgencyCode(),
                this.accountInfoModel.getAccountNo(),
                this.accountInfoModel.getAccountType(),
                this.accountInfoModel.getUpdateVersion(),
                newUpdateVersion,
                billNo, amount,
                accountTradeType,
                AccountBaseOperation.UNFREEZE, postscript);
        return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.UNFREEZE, postscript,newUpdateVersion);
    }


    /**
     * 锁定账户
     * 锁定状态的账户不能进行任何交易，
     *
     * @return
     * @throws Exception
     */
    protected final boolean lock() {
        return updateAccountStatus(AccountStatus.LOCKED, AccountStatus.NORMAL);
    }


    /**
     * 解锁账户
     * 将账户从锁定状态变为正常状态
     *
     * @return
     * @throws Exception
     */
    protected final boolean unlock() {
        return updateAccountStatus(AccountStatus.NORMAL, AccountStatus.LOCKED);
    }


    /**
     * 失效，失效之后的账户不能在进行任何操作，也不可以再恢复到正常状态
     *
     * @return
     * @throws Exception
     */
    protected final boolean invalid() {
        return updateAccountStatus(AccountStatus.INVALID, AccountStatus.LOCKED);
    }


    /**
     * 记账
     *
     * @param amount           金额
     * @param accountTradeType 账户操作类型
     * @param billNo           账单编号
     * @param bookkeeping      记账类型
     * @Param postscript       附言
     * @Param newFrozenUpdateVersion  冻结账户版本号
     * @return
     * @throws Exception
     */
    private boolean bookkeeping(BigDecimal amount, AccountTradeType accountTradeType, String billNo, AccountBaseOperation bookkeeping, String postscript,Long newFrozenUpdateVersion) {
        boolean returnFlag = false;

        Long newUpdateVersion = sequence.nextId();

        //#1.记录出账流水
        AccountSerialModel newAccountSerial
                = accountSerialService.createAccountSerial(this.accountInfoModel.getUserId(),
                this.getAccountInfoModel().getAgencyCode(),
                this.accountInfoModel.getAccountNo(),
                this.accountInfoModel.getAccountType(),
                this.accountInfoModel.getUpdateVersion(),
                newUpdateVersion,billNo, amount,
                accountTradeType, bookkeeping, postscript);

        //#2.变更账户余额
        AccountInfoModel updateResult
                = accountInfoService.updateFinalBalance(this.accountInfoModel.getAccountNo(),
                newAccountSerial.getChangeAmount(),
                newAccountSerial.getFinalBalance(),
                this.accountInfoModel.getUpdateVersion(),
                newUpdateVersion,
                bookkeeping,
                this.accountInfoModel.getFrozenUpdateVersion(),
                newFrozenUpdateVersion);

        if (!ValidationUtil.isEmpty(updateResult)) {
            returnFlag = true;
            //#3.刷新账户信息
            this.refresh(this.accountInfoModel.getAccountNo());
        }
        return returnFlag;
    }


    /**
     * 变更账户状态
     *
     * @param newStatus
     * @param oldStatus
     * @return
     * @throws Exception
     */
    private boolean updateAccountStatus(AccountStatus newStatus, AccountStatus oldStatus) {
        boolean returnFlag = false;
        AccountInfoModel updateResult = accountInfoService.updateAccountStatus(accountInfoModel.getAccountNo(), newStatus, oldStatus, this.accountInfoModel.getUpdateVersion());

        if (!ValidationUtil.isEmpty(updateResult)) {
            returnFlag = true;
            this.refresh(this.accountInfoModel.getAccountNo());
        }
        return returnFlag;
    }


    public final AccountInfoModel getAccountInfoModel() {
        return accountInfoModel;
    }


    private void refresh(String accountNo) {

        this.accountInfoModel = accountInfoService.queryByAccontNo(accountNo);
    }

    private static Account newInstance(AccountInfoModel accountInfoModel) {
        Account account = new Account();
        account.accountInfoModel = accountInfoModel;
        return account;
    }


    protected final Account setAccountInfo(AccountInfoModel accountInfoModel) {
        return newInstance(accountInfoModel);
    }


    public static Account getAccountInstance(String userId,String agencyCode, AccountType accountType) {
        AccountInfoModel accountInfoModel = accountInfoService.queryByUserId(userId, accountType.getAccountType());
        if (ValidationUtil.isEmpty(accountInfoModel))
            accountInfoModel = initAccountInfo(userId,agencyCode, accountType);
        return newInstance(accountInfoModel);
    }

    public static Account getAccountInstance(String accountNo) {
        return newInstance(accountInfoService.queryByAccontNo(accountNo));
    }


    private static String createAccountNo(String userId, AccountType accountType) {
        return userId + accountType.getAccountNoSuffix();
    }


}
