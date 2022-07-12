package org.typroject.tyboot.prototype.order.state;

import org.typroject.tyboot.prototype.order.rule.OperationLimitHandler;

/**
 * <pre>
 *
 *  File: DefaultBranchOperationType.java
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  DefaultBranchOperationType.java  tyrest\magintursh
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public enum DefaultBranchOperationType implements BranchOperationType {
    /**
     * 结账/支付
     */
    CHECKOUT("结账", null, null),

    /**
     * 取消，即提前终止流程流转
     */
    CANCEL("取消", null, null),

    /**
     * 退款
     */
    REFUND("", null, null);


    private String operationName;
    private Class<? extends OperationLimitHandler> oprationRuleHandler;
    private Class<? extends BranchHandler> branchHandler;

    private DefaultBranchOperationType(String operationName, Class<? extends OperationLimitHandler> oprationRuleHandler, Class<? extends BranchHandler> branchHandler) {
        this.operationName = operationName;
        this.oprationRuleHandler = oprationRuleHandler;
        this.branchHandler = branchHandler;
    }


    @Override
    public String getOperationName() {

        return this.operationName;
    }

    @Override
    public String getOperationCode() {

        return this.name();
    }

    @Override
    public Class<? extends OperationLimitHandler> getOprationRuleHandler() {
        return this.oprationRuleHandler;
    }


    @Override
    public Class<? extends BranchHandler> getBranchHandler() {
        return this.branchHandler;
    }

}

/*
 *$Log: av-env.bat,v $
 */