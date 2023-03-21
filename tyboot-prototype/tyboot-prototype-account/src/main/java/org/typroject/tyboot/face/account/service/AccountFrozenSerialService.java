package org.typroject.tyboot.face.account.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.account.model.AccountFrozenSerialModel;
import org.typroject.tyboot.face.account.orm.dao.AccountFrozenSerialMapper;
import org.typroject.tyboot.face.account.orm.entity.AccountFrozenSerial;
import org.typroject.tyboot.prototype.account.AccountBaseOperation;
import org.typroject.tyboot.prototype.account.AccountTradeException;
import org.typroject.tyboot.prototype.account.trade.AccountTradeType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 虚拟账户金额变更记录表，所有针对账户金额的变动操作都要记录到此表中， 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
@Component
public class AccountFrozenSerialService extends BaseService<AccountFrozenSerialModel, AccountFrozenSerial, AccountFrozenSerialMapper> {

    public AccountFrozenSerialModel createAccountFrozenSerial(String userId,
                                                              String agencyCode,
                                                              String accountNo,
                                                              String accountType,
                                                              Long oldUpdateVersion,
                                                              Long newUpdateVersion,
                                                              String billNo,
                                                              BigDecimal amount,
                                                              AccountTradeType accountTradeType,
                                                              AccountBaseOperation bookkeeping,
                                                              String postscript) {
        //#1.查询当前流水记录
        AccountFrozenSerialModel lastAccountSerial = this.queryLastAccountFrozenSerial(accountNo);

        BigDecimal initialBalance = new BigDecimal(0);        //起始金额
        if (!ValidationUtil.isEmpty(lastAccountSerial)) {
            if(!lastAccountSerial.getUpdateVersion().equals(oldUpdateVersion)){
                throw new AccountTradeException("账户异常!","冻结账户信息与冻结流水记录的版本号不一致.");
            }
            initialBalance = lastAccountSerial.getFinalBalance();
        }
        BigDecimal finalBalance = this.calaFinalBalance(bookkeeping, amount, initialBalance);//最终余额

        //#2.创建流水记录
        AccountFrozenSerialModel newAccountSerial = new AccountFrozenSerialModel();
        newAccountSerial.setAccountNo(accountNo);
        newAccountSerial.setBillNo(billNo);
        newAccountSerial.setChangeAmount(amount);
        newAccountSerial.setFinalBalance(finalBalance);
        newAccountSerial.setInitialPrefundedBalance(initialBalance);
        newAccountSerial.setOperateTime(new Date());
        newAccountSerial.setOperationType(accountTradeType.getAccountTradeType());
        newAccountSerial.setUserId(userId);
        newAccountSerial.setAgencyCode(agencyCode);
        newAccountSerial.setUpdateVersion(newUpdateVersion);
        newAccountSerial.setAccountType(accountType);
        newAccountSerial.setBookkeeping(bookkeeping.name());
        newAccountSerial.setPostscript(postscript);
        return this.createWithModel(newAccountSerial);
    }

    public AccountFrozenSerialModel queryLastAccountFrozenSerial(String accountNo) {

        AccountFrozenSerialModel accountSerialModel = null;
        List<AccountFrozenSerialModel> list = queryForTopList(1, "SEQUENCE_NBR", false, accountNo);

        if (!ValidationUtil.isEmpty(list))
            accountSerialModel = list.get(0);
        return accountSerialModel;
    }


    /**
     * 计算最终余额
     *
     * @param bookkeeping
     * @param amount
     * @param initialBalance
     * @return
     */
    private BigDecimal calaFinalBalance(AccountBaseOperation bookkeeping, BigDecimal amount, BigDecimal initialBalance) {
        BigDecimal finalBalance;
        switch (bookkeeping) {
            case FREEZE:
                finalBalance = initialBalance.add(amount);
                break;
            case RELEASE_FROZEN:
            case UNFREEZE:
                finalBalance = initialBalance.subtract(amount);
                if (finalBalance.doubleValue() < 0) {
                    throw new AccountTradeException("冻结账户余额不足.");
                }
                break;
            default:
                throw new AccountTradeException("账户操作类型有误.");
        }
        return finalBalance;

    }

    public Page querySerialPage(Page page, String accountNo, String accountType, String userId) {
        return this.queryForPage(page, "OPERATE_TIME", false, accountNo, accountType, userId);
    }

}
