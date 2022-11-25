package org.typroject.tyboot.face.account.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

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
@TableName("account_exchange_rate" )
public class AccountExchangeRate extends BaseEntity {

    private static final long serialVersionUID = 65654353532L;

    /**
     * 来源账户类型（币种）
     */
    @TableField("SOURCE_ACCOUNT_TYPE" )
    private String sourceAccountType;

    /**
     * 来源货币兑换目标货币的汇率
     */
    @TableField("EXCHANGE_RATE" )
    private String exchangeRate;

    /**
     * 目标账户类型（币种）
     */
    @TableField("TARGET_ACCOUNT_TYPE" )
    private String targetAccountType;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME" )
    private String createTime;

    /**
     * 目标货币兑换来源货币的汇率
     */
    @TableField("INVERSE_EXCHANGE_RATE" )
    private String inverseExchangeRate;

}
