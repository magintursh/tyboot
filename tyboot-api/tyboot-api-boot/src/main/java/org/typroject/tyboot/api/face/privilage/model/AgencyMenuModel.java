package org.typroject.tyboot.api.face.privilage.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 角色与菜单关系表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class AgencyMenuModel extends BaseModel {

    private static final long serialVersionUID = 144894231L;


	private String agencyCode;
    /**
     * 菜单ID
     */
	private String menuSequenceNbr;
	private String createUserId;
	private Date createTime;


	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getMenuSequenceNbr() {
		return menuSequenceNbr;
	}

	public void setMenuSequenceNbr(String menuSequenceNbr) {
		this.menuSequenceNbr = menuSequenceNbr;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
