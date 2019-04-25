package org.typroject.tyboot.api.face.privilage.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class RoleModel extends BaseModel {

    private static final long serialVersionUID = 78673258745325L;

    /**
     * 角色名称
     */
	private String roleName;
    /**
     * 机构编码
     */
	private String agencyCode;
    /**
     * 创建时间
     */
	private Date createTime;

    /**
     * 数据锁定状态: N :非锁定 / Y: 锁定 
     */
	private String lockStatus;
    /**
     * 数据锁定时间
     */
	private Date lockDate;
    /**
     * 锁定人ID外键
     */
	private String lockUserId;

    /**
     * 创建人
     */
	private String createUserId;


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockUserId() {
		return lockUserId;
	}

	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}


	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


}
