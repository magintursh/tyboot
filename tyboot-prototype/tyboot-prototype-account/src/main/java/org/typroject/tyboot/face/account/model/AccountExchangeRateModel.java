package org.typroject.tyboot.face.account.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.typroject.tyboot.core.rdbms.model.BaseModel;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * <p>
 * 账户兑换汇率
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountExchangeRateModel extends BaseModel {

    private static final long serialVersionUID = 6365754333333L;

    /**
     * 来源账户类型（币种）
     */
    private String sourceAccountType;

    /**
     * 来源货币兑换目标货币的汇率
     */
    private BigDecimal exchangeRate;

    /**
     * 目标账户类型（币种）
     */
    private String targetAccountType;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 目标货币兑换来源货币的汇率
     */
    private BigDecimal inverseExchangeRate;

}
