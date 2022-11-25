package org.typroject.tyboot.face.account.orm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.typroject.tyboot.face.account.orm.entity.AccountExchangeRate;

/**
 * <p>
  * 用户虚拟账户表，记录所有公网用户的虚拟账户，account_no字段预留，用以多账户支持 Mapper 接口
 * </p>
 *
 * @author 子杨
 * @since 2018-01-23
 */
public interface AccountExchangeRateMapper extends BaseMapper<AccountExchangeRate> {

}