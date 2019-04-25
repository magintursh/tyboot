package org.typroject.tyboot.api.face.privilage.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class UserRoleModel extends BaseModel {

    private static final long serialVersionUID = 5587398945434L;


    /**
     * 机构编码
     */
	private String agencyCode;
    /**
     * 机构用户表外键
     */
	private String userId;
    /**
     * 角色表外键
     */
	private String roleSequenceNbr;



	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleSequenceNbr() {
		return roleSequenceNbr;
	}

	public void setRoleSequenceNbr(String roleSequenceNbr) {
		this.roleSequenceNbr = roleSequenceNbr;
	}


}
