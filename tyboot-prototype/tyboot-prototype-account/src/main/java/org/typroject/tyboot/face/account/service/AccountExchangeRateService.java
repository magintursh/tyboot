package org.typroject.tyboot.face.account.service;


import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.account.model.AccountExchangeRateModel;
import org.typroject.tyboot.face.account.orm.dao.AccountExchangeRateMapper;
import org.typroject.tyboot.face.account.orm.entity.AccountExchangeRate;


/**
 * <p>
 * 账户兑换汇率
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
@Component
public class AccountExchangeRateService extends BaseService<AccountExchangeRateModel, AccountExchangeRate, AccountExchangeRateMapper> {

    public AccountExchangeRateModel queryByAccountType(String sourceAccountType, String targetAccountType) {
        return this.queryModelByParams(sourceAccountType, targetAccountType);
    }




}
