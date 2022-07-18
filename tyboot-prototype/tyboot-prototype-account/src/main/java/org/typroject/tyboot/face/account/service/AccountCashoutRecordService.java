package org.typroject.tyboot.face.account.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.exception.BaseException;
import org.typroject.tyboot.core.foundation.utils.Sequence;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.account.model.AccountCashoutModel;
import org.typroject.tyboot.face.account.orm.dao.AccountCashoutRecordMapper;
import org.typroject.tyboot.face.account.orm.entity.AccountCashout;
import org.typroject.tyboot.prototype.account.AccountType;

import java.math.BigDecimal;


/**
 * <p>
 * 提现申请表 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
@Component
public class AccountCashoutRecordService extends BaseService<AccountCashoutModel, AccountCashout, AccountCashoutRecordMapper> {

    /**
     * 提现状态-待手工处理-大于指定金额的提现申请 需要后台审核之后才能实际转账
     */
    public static final String CASHOUT_STATUS_SUSPEND = "SUSPEND";

    /**
     * 提现状态-待自动处理
     */
    public static final String CASHOUT_STATUS_SUSPEND_AUTO = "SUSPEND_AUTO";

    /**
     * 提现状态-已转账
     */
    public static final String CASHOUT_STATUS_TRANSFERRED = "TRANSFERRED";

    /**
     * 提现状态-处理中
     */
    public static final String CASHOUT_STATUS_PENDING = "PENDING";

    /**
     * 提现状态-提现失败
     */
    public static final String CASHOUT_STATUS_FAILED = "FAILED";


    public AccountCashoutModel queryByApplyNo(String applayNo) {
        return queryModelByParams(applayNo);
    }


    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public AccountCashoutModel createCashoutRecord(AccountCashoutModel cashoutRecordModel, AccountType accountType) throws Exception {
        cashoutRecordModel.setUserId(RequestContext.getExeUserId());
        cashoutRecordModel.setApplayNo(Sequence.generatorBillNo());
        cashoutRecordModel.setApplyStatus(CASHOUT_STATUS_SUSPEND);
        //cashoutRecordModel.setOutAmount(cashoutRecordModel.getApplayAmount().subtract(cashoutRecordModel.getApplayAmount().multiply(TARIFF)));
        cashoutRecordModel.setOutAmount(cashoutRecordModel.getApplayAmount());
        //cashoutRecordModel.setPoundage(cashoutRecordModel.getApplayAmount().multiply(TARIFF));
        cashoutRecordModel.setPoundage(new BigDecimal(0));
        cashoutRecordModel.setApplayType("ALIPAY");
        return this.createWithModel(cashoutRecordModel);
    }




    /**
     * 确认申请记录，
     *
     * @param applyNo
     * @return
     * @throws Exception
     */
    public AccountCashoutModel comfirm(String applyNo) throws Exception {
        AccountCashoutModel cashoutRecordModel = this.queryByApplyNo(applyNo);
        if (!ValidationUtil.isEmpty(cashoutRecordModel) && CASHOUT_STATUS_SUSPEND.equals(cashoutRecordModel.getApplyStatus())) {
            cashoutRecordModel.setApplyStatus(CASHOUT_STATUS_PENDING);
            this.updateWithModel(cashoutRecordModel);
        } else {
            throw new Exception("找不到指定的申请记录.");
        }


        return cashoutRecordModel;
    }


    /**
     * 转账确认，从冻结账户出账所申请的资金，将申请记录设置为体现成功
     *
     * @param applyNo
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public AccountCashoutModel transferComfirm(String applyNo) throws Exception {
        AccountCashoutModel cashoutRecordModel = this.queryByApplyNo(applyNo);
        if (!ValidationUtil.isEmpty(cashoutRecordModel) && CASHOUT_STATUS_PENDING.equals(cashoutRecordModel.getApplyStatus())) {

            //将申请记录设置为提现成功
            cashoutRecordModel.setApplyStatus(CASHOUT_STATUS_TRANSFERRED);
            this.updateWithModel(cashoutRecordModel);
        } else {
            throw new Exception("找不到指定的申请记录.");
        }
        return cashoutRecordModel;
    }


    /**
     * 拒绝申请
     *
     * @param applyNo
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public AccountCashoutModel refuse(String applyNo, AccountType accountType) throws Exception {
        AccountCashoutModel cashoutRecordModel = this.queryByApplyNo(applyNo);
        if (!ValidationUtil.isEmpty(cashoutRecordModel) && CASHOUT_STATUS_SUSPEND.equals(cashoutRecordModel.getApplyStatus())) {


            cashoutRecordModel.setApplyStatus(CASHOUT_STATUS_FAILED);
            this.updateWithModel(cashoutRecordModel);
        } else {
            throw new Exception("找不到指定的申请记录.");
        }
        return cashoutRecordModel;
    }


    public Page queryForRecordPage(Page page, String applyStatus) throws Exception {
        return this.queryForPage(page, null, false, applyStatus);
    }


}
