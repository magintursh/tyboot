package org.typroject.tyboot.api.face.privilage.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class MenuModel extends BaseModel {

    private static final long serialVersionUID = 4894512315489L;


	private String agencyCode;
    /**
     * 父菜单ID
     */
	private String parentId;
    /**
     * 菜单名称
     */
	private String menuName;
    /**
     * 菜单类型（目录、菜单、页面）
     */
	private String menuType;
    /**
     * 菜单图标
     */
	private String menuIcon;
    /**
     * 菜单标题
     */
	private String menuTitle;
    /**
     * 菜单层级
     */
	private Integer menuLevel;
    /**
     * 排序
     */
	private Integer orderNum;
    /**
     * 创建人
     */
	private String createUserId;
    /**
     * 创建时间
     */
	private Date createTime;



	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
