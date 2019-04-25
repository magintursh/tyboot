package org.typroject.tyboot.core.auth.face.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TableName("auth_login_info")
public class LoginInfo extends BaseEntity {

    private static final long serialVersionUID = 1854894151651564L;


    /**
     * 登录ID
     */
	@TableField("LOGIN_ID")
	private String loginId;
    /**
     * 系统用户ID
     */
	@TableField("USER_ID")
	private String userId;
	@TableField("PASSWORD")
	private String password;
    /**
     * 密码随机盐
     */
	@TableField("SALT")
	private String salt;
	@TableField("USER_TYPE")
	private String userType;
	@TableField("AGENCY_CODE")
	private String agencyCode;

	@TableField("ID_TYPE")
	private String idType;

    /**
     * 数据锁定状态: N :非锁定 / Y: 锁定
     */
	@TableField("LOCK_STATUS")
	private String lockStatus;
    /**
     * 数据锁定时间
     */
	@TableField("LOCK_DATE")
	private Date lockDate;
    /**
     * 锁定人ID外键
     */
	@TableField("LOCK_USER_ID")
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