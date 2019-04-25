package org.typroject.tyboot.api.face.privilage.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 角色与菜单关系表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TableName("privilege_role_menu")
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 4564212318978L;

    /**
     * 机构编号
     */
	@TableField("AGENCY_CODE")
	private String agencyCode;
    /**
     * 角色ID
     */
	@TableField("ROLE_SEQUENCE_NBR")
	private String roleSequenceNbr;
    /**
     * 菜单ID
     */
	@TableField("MENU_SEQUENCE_NBR")
	private String menuSequenceNbr;
	@TableField("CREATE_USER_ID")
	private String createUserId;
	@TableField("CREATE_TIME")
	private Date createTime;



	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getRoleSequenceNbr() {
		return roleSequenceNbr;
	}

	public void setRoleSequenceNbr(String roleSequenceNbr) {
		this.roleSequenceNbr = roleSequenceNbr;
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
