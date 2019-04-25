package org.typroject.tyboot.core.auth.face.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class LoginInfoModel extends BaseModel {

    private static final long serialVersionUID = 48465411351535L;


    /**
     * 登录ID
     */
	private String loginId;
    /**
     * 系统用户ID
     */
	private String userId;
	private String password;
    /**
     * 密码随机盐
     */
	private String salt;
	private String userType;
	private String agencyCode;

	private String idType;
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


	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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


}
