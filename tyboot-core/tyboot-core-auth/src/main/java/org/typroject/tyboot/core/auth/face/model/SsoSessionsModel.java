package org.typroject.tyboot.core.auth.face.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 登录SESSION
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class SsoSessionsModel extends BaseModel {

    private static final long serialVersionUID = 4645613132132132L;

    /**
     * 归属机构
     */
	private String agencyCode;
    /**
     * 登录账号
     */
	private String loginId;
    /**
     * 用户编号
     */
	private String userId;
    /**
     * 用户名
     */
	private String userName;
    /**
     * 用户类型
     */
	private String userType;
    /**
     * 来源产品
     */
	private String actionByProduct;
    /**
     * 来源IP
     */
	private String actionByIp;
    /**
     * 过期时限(秒）
     */
	private Long sessionExpiration;
    /**
     * session状态：激活，过期
     */
	private String sessionStatus;
    /**
     * 创建时间
     */
	private Date sessionCreateTime;
	private String actionByAgent;
    /**
     * 来源设备串码
     */
	private String sourceDeviceCode;
    /**
     * 来源平台
     */
	private String sourceOs;


	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getActionByProduct() {
		return actionByProduct;
	}

	public void setActionByProduct(String actionByProduct) {
		this.actionByProduct = actionByProduct;
	}

	public String getActionByIp() {
		return actionByIp;
	}

	public void setActionByIp(String actionByIp) {
		this.actionByIp = actionByIp;
	}

	public Long getSessionExpiration() {
		return sessionExpiration;
	}

	public void setSessionExpiration(Long sessionExpiration) {
		this.sessionExpiration = sessionExpiration;
	}

	public String getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public Date getSessionCreateTime() {
		return sessionCreateTime;
	}

	public void setSessionCreateTime(Date sessionCreateTime) {
		this.sessionCreateTime = sessionCreateTime;
	}

	public String getActionByAgent() {
		return actionByAgent;
	}

	public void setActionByAgent(String actionByAgent) {
		this.actionByAgent = actionByAgent;
	}

	public String getSourceDeviceCode() {
		return sourceDeviceCode;
	}

	public void setSourceDeviceCode(String sourceDeviceCode) {
		this.sourceDeviceCode = sourceDeviceCode;
	}

	public String getSourceOs() {
		return sourceOs;
	}

	public void setSourceOs(String sourceOs) {
		this.sourceOs = sourceOs;
	}

}
