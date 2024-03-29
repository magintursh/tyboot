package org.typroject.tyboot.prototype.account.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.prototype.account.Account;
import org.typroject.tyboot.prototype.account.trade.AccountTradeHandler;
import org.typroject.tyboot.prototype.account.trade.BaseTradeParams;
import org.typroject.tyboot.prototype.account.trade.DefaultAccountTradeType;
import org.typroject.tyboot.prototype.account.trade.TradeParams;
import org.typroject.tyboot.face.account.service.AccountRechargeRecordService;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Map;

/**
 * <pre>
 *  Tyrest
 *  File: RechargeHandler.java
 *
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: RechargeHandler.java  Tyrest\magintrursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "rechargeHandler")
public class RechargeHandler implements AccountTradeHandler {

    @Autowired
    private AccountRechargeRecordService accountRechargeRecordService;


    /**
     * 交易参数
     */
    public enum RechargeParams implements TradeParams {

        billNo(true, "账单号"),       //用户
        amount(true, "交易金额"),   //交易金额
        tradeChannel(false, "法币交易渠道"),       //法币交易渠道
        tradeType(false, "交易渠道的交易类型"),   //交易渠道的交易类型
        postscript(true, "交易附言"); //附言

        private boolean notnull;
        private String paramName;

        RechargeParams(boolean notnull, String paramName) {
            this.notnull = notnull;
            this.paramName = paramName;
        }

        public boolean isNotnull() {
            return notnull;
        }

        public String getParesStr() {
            return paramName;
        }

        public String getParamCode() {
            return this.name();
        }

        @Override
        public <T> T getValue(Map<String, Object> params) {
            return (T) params.get(this.getParamCode());
        }


    }


    @Override
    public boolean execute(Map<String, Object> params, Account account) {
        boolean flage = false;
        //解析参数
        if (BaseTradeParams.checkPrams(params, RechargeParams.values())) {
            BigDecimal amount = RechargeParams.amount.getValue(params);
            String billNo = RechargeParams.billNo.getValue(params);
            String postscript = RechargeParams.postscript.getValue(params);
            String tradeChannel = RechargeParams.tradeChannel.getValue(params);
            String tradeType = RechargeParams.tradeType.getValue(params);
            //执行交易
            flage = account.income(amount, DefaultAccountTradeType.RECHARGE, billNo, postscript);
            if (flage)
                accountRechargeRecordService.createRecord(account.getAccountInfoModel().getUserId(),
                        account.getAccountInfoModel().getAccountNo(),
                        amount, billNo, account.getAccountInfoModel().getAccountType(), tradeChannel, tradeType);
        }
        return flage;
    }


}