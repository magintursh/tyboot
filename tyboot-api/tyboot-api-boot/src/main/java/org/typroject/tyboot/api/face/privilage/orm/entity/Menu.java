package org.typroject.tyboot.api.face.privilage.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TableName("privilege_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 4541312312312315848L;


	@TableField("AGENCY_CODE")
	private String agencyCode;
    /**
     * 父菜单ID
     */
	@TableField("PARENT_ID")
	private String parentId;
    /**
     * 菜单名称
     */
	@TableField("MENU_NAME")
	private String menuName;
    /**
     * 菜单类型（目录、菜单、页面）
     */
	@TableField("MENU_TYPE")
	private String menuType;
    /**
     * 菜单图标
     */
	@TableField("MENU_ICON")
	private String menuIcon;
    /**
     * 菜单标题
     */
	@TableField("MENU_TITLE")
	private String menuTitle;
    /**
     * 菜单层级
     */
	@TableField("MENU_LEVEL")
	private Integer menuLevel;
    /**
     * 排序
     */
	@TableField("ORDER_NUM")
	private Integer orderNum;
    /**
     * 创建人
     */
	@TableField("CREATE_USER_ID")
	private String createUserId;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
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
