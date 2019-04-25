package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 系统字典表数据
 * </p>
 *
 * @author magintursh
 * @since 2017-06-27
 */
@TableName("systemctl_dictionarie_value")
public class DictionarieValue extends BaseEntity {

    private static final long serialVersionUID = 454515151231231L;

    /**
     * 字典编码(系统中固定不变的)
     */
	@TableField("DICT_CODE")
	private String dictCode;
    /**
     * 字典KEY(当前字典中唯一)
     */
	@TableField("DICT_DATA_KEY")
	private String dictDataKey;
	@TableField("DICT_DATA_VALUE")
	private String dictDataValue;
    /**
     * 字典VALUE描述
     */
	@TableField("DICT_DATA_DESC")
	private String dictDataDesc;
    /**
     * 排序字段
     */
	@TableField("ORDER_NUM")
	private Integer orderNum;
    /**
     * 机构编码
     */
	@TableField("AGENCY_CODE")
	private String agencyCode;

	@TableField("LOCK_STATUS")
	private String lockStatus;
	@TableField("LOCK_USER_ID")
	private String lockUserId;
	@TableField("LOCK_DATE")
	private Date lockDate;



	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictDataKey() {
		return dictDataKey;
	}

	public void setDictDataKey(String dictDataKey) {
		this.dictDataKey = dictDataKey;
	}

	public String getDictDataValue() {
		return dictDataValue;
	}

	public void setDictDataValue(String dictDataValue) {
		this.dictDataValue = dictDataValue;
	}

	public String getDictDataDesc() {
		return dictDataDesc;
	}

	public void setDictDataDesc(String dictDataDesc) {
		this.dictDataDesc = dictDataDesc;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}


	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLockUserId() {
		return lockUserId;
	}

	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}


}
