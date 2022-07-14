package org.typroject.tyboot.face.account.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.constans.CoreConstans;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.utils.Bean;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.account.model.AccountInfoModel;
import org.typroject.tyboot.face.account.orm.dao.AccountInfoMapper;
import org.typroject.tyboot.face.account.orm.entity.AccountInfo;
import org.typroject.tyboot.prototype.account.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 用户虚拟账户表，记录所有公网用户的虚拟账户，account_no字段预留，用以多账户支持 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
@Component
public class AccountInfoService extends BaseService<AccountInfoModel, AccountInfo, AccountInfoMapper> {


    @Autowired
    private Sequence sequence;

    public AccountInfoModel initAccountInfo(String userId, AccountType accountType, String accountNo) {
        AccountInfoModel accountInfo = new AccountInfoModel();
        accountInfo.setAccountNo(accountNo);//账户编号生成
        accountInfo.setUserId(userId);
        accountInfo.setAccountType(accountType.getAccountType());
        accountInfo.setAccountStatus(AccountStatus.NORMAL.name());
        accountInfo.setAgencyCode(CoreConstans.CODE_SUPER_ADMIN);
        accountInfo.setBalance(BigDecimal.ZERO);
        accountInfo.setCreateTime(new Date());
        accountInfo.setCumulativeBalance(BigDecimal.ZERO);
        accountInfo.setSpendAmount(BigDecimal.ZERO);
        accountInfo.setFrozenBalance(BigDecimal.ZERO);
        accountInfo.setPaymentPassword(AccountConstants.DEFAULT_PAYMENT_PASSWORD);
        accountInfo.setUpdateVersion(sequence.nextId());
        return this.createWithModel(accountInfo);
    }


    /**
     * 更新账户余额
     * @param accountNo  账户编号
     * @param changeAmount  变更金额
     * @param finalBalance  最终金额
     * @param oldUpdateVersion  记录版本号
     * @param bookkeeping  记账类型
     * @return
     * @throws Exception
     */
    public AccountInfoModel updateFinalBalance(String accountNo, BigDecimal changeAmount,BigDecimal finalBalance, Long oldUpdateVersion, AccountBaseOperation bookkeeping)  {
        AccountInfoModel oldModel = this.queryByAccontNoAndVersion(accountNo, oldUpdateVersion);
        if (!ValidationUtil.isEmpty(oldModel)) {
            oldModel.setBalance(finalBalance);
            if(ValidationUtil.isEmpty(oldModel.getCumulativeBalance())){
                oldModel.setCumulativeBalance(BigDecimal.ZERO);
            }
            if(ValidationUtil.isEmpty(oldModel.getSpendAmount())){
                oldModel.setSpendAmount(BigDecimal.ZERO);
            }

            switch (bookkeeping){
                case INCOME:
                    oldModel.setCumulativeBalance(oldModel.getCumulativeBalance().add(changeAmount));
                    break;
                case SPEND:
                    oldModel.setSpendAmount(oldModel.getSpendAmount().add(changeAmount));
                    break;
                case FREEZE:
                    oldModel.setSpendAmount(oldModel.getSpendAmount().add(changeAmount));
                    oldModel.setFrozenBalance(oldModel.getFrozenBalance().add(changeAmount));
                    break;
                case UNFREEZE:
                    if(oldModel.getFrozenBalance().doubleValue() < changeAmount.doubleValue()){
                        throw new AccountTradeException("冻结账户金额不足.");
                    }
                    oldModel.setCumulativeBalance(oldModel.getCumulativeBalance().add(changeAmount));
                    oldModel.setFrozenBalance(oldModel.getFrozenBalance().subtract(changeAmount));
                    break;
                default:
                    throw new AccountTradeException("账户操作类型有误." );
            }

            oldModel.setUpdateVersion(sequence.nextId());
            oldModel.setRecDate(new Date());
            oldModel.setRecUserId(RequestContext.getExeUserId());


            Map<String, Object> params = new HashMap<>();
            params.put("UPDATE_VERSION" , oldUpdateVersion);
            params.put("ACCOUNT_NO" , accountNo);
            params.put("ACCOUNT_STATUS" , AccountStatus.NORMAL.name());
            QueryWrapper<AccountInfo> wrapper = new QueryWrapper<>();
            wrapper.allEq(params, Boolean.FALSE);
            boolean result = this.update(Bean.toPo(oldModel, new AccountInfo()), wrapper);
            if (!result)
                throw new AccountTradeException("更新余额失败." );
        } else {
            throw new AccountTradeException("找不到指定账户." );
        }
        return oldModel;
    }

    /**
     * 释放冻结中的金额
     * @param accountNo  账户编号
     * @param changeAmount  要释放的金额
     * @param oldUpdateVersion 旧的账户版本号
     * @return
     */
    public AccountInfoModel  releaseFrozen(String accountNo,BigDecimal changeAmount, Long oldUpdateVersion){
        AccountInfoModel oldModel = this.queryByAccontNoAndVersion(accountNo, oldUpdateVersion);
        if(!ValidationUtil.isEmpty(oldModel)){
            if(oldModel.getFrozenBalance().doubleValue() < changeAmount.doubleValue()){
                throw new AccountTradeException("释放冻结金额失败.冻结金额异常");
            }
            BigDecimal frozenBalance = oldModel.getFrozenBalance().subtract(changeAmount);
            oldModel.setFrozenBalance(frozenBalance);

            oldModel.setUpdateVersion(sequence.nextId());
            oldModel.setRecDate(new Date());
            oldModel.setRecUserId(RequestContext.getExeUserId());

            Map<String, Object> params = new HashMap<>();
            params.put("UPDATE_VERSION" , oldUpdateVersion);
            params.put("ACCOUNT_NO" , accountNo);
            params.put("ACCOUNT_STATUS" , AccountStatus.NORMAL.name());
            QueryWrapper<AccountInfo> wrapper = new QueryWrapper<>();
            wrapper.allEq(params, Boolean.FALSE);
            boolean result = this.update(Bean.toPo(oldModel, new AccountInfo()), wrapper);
            if (!result)
                throw new AccountTradeException("更新余额失败." );

        } else {
            throw new AccountTradeException("找不到指定账户." );
        }
        return oldModel;

    }


    public AccountInfoModel updateAccountStatus(String accountNo, AccountStatus newStatus, AccountStatus oldStatus, Long oldUpdateVersion)  {
        AccountInfoModel oldModel = this.queryByAccontNoAndVersion(accountNo, oldUpdateVersion);
        if (!ValidationUtil.isEmpty(oldModel)) {
            oldModel.setAccountStatus(newStatus.name());
            oldModel.setRecDate(new Date());
            oldModel.setRecUserId(RequestContext.getExeUserId());

            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("UPDATE_VERSION" , oldUpdateVersion);
            wrapper.eq("ACCOUNT_NO" , accountNo);
            wrapper.eq("PAYMENT_PASSWORD" , AccountConstants.DEFAULT_PAYMENT_PASSWORD);
            if (!ValidationUtil.isEmpty(oldStatus))
                wrapper.eq("ACCOUNT_STATUS" , oldStatus.name());
            boolean result = this.update(Bean.toPo(oldModel, new AccountInfo()), wrapper);
            if (!result)
                throw new AccountTradeException("更新状态失败." );
        } else {
            throw new AccountTradeException("找不到指定账户." );
        }
        return oldModel;
    }


    public AccountInfoModel queryByAccontNo(String accountNo)  {
        return this.queryModelByParams(accountNo);
    }

    public AccountInfoModel queryByAccontNoAndVersion(String accountNo, Long updateVersion) {
       return this.queryModelByParams(accountNo,updateVersion);
    }

    public AccountInfoModel queryByUserId(String userId, AccountType accountType) {
        AccountInfoModel accountInfoModel = new AccountInfoModel();
        accountInfoModel.setUserId(userId);
        accountInfoModel.setAccountType(accountType.getAccountType());
        return this.queryByModel(accountInfoModel);
    }
}
