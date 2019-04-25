package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 系统字典表数据
 * </p>
 *
 * @author magintursh
 * @since 2017-06-27
 */
public class 	DictionarieValueModel extends BaseModel {

    private static final long serialVersionUID = 4894465489456L;


    /**
     * 字典编码(系统中固定不变的)
     */
	private String dictCode;
    /**
     * 字典KEY(当前字典中唯一)
     */
	private String dictDataKey;
	private String dictDataValue;
    /**
     * 字典VALUE描述
     */
	private String dictDataDesc;
    /**
     * 排序字段
     */
	private Integer orderNum;
    /**
     * 机构编码
     */
	private String agencyCode;

	private String lockStatus;
	private String lockUserId;
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
