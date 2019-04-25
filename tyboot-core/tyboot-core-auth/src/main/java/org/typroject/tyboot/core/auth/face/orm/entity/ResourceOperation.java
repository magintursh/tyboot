package org.typroject.tyboot.core.auth.face.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

/**
 * <p>
 * 资源操作表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
@TableName("auth_resource_operation")
public class ResourceOperation extends BaseEntity {

    private static final long serialVersionUID = 89784513154165465L;


    /**
     * 模块代码，程序中的注解
     */
	@TableField("MODULE_CODE")
	private String moduleCode;
    /**
     * 资源代码，程序中的注解
     */
	@TableField("RESOURCE_CODE")
	private String resourceCode;
    /**
     * 操作代码，程序中的注解
     */
	@TableField("OPRATE_CODE")
	private String oprateCode;
    /**
     * 按约定规则识别操作的唯一的标识FXXXXXXXXXX(F后面8位数字加2位字符)
     */
	@TableField("API_CODE")
	private String apiCode;
    /**
     * 父主键UID
     */
	@TableField("PARENT_ID")
	private String parentId;
    /**
     * 资源类型(模块、资源、操作)
     */
	@TableField("RES_TYPE")
	private String resType;
    /**
     * 操作级别代码，程序中的注解
     */
	@TableField("LEVEL_CODE")
	private String levelCode;
    /**
     * 模块名称
     */
	@TableField("MODULE_NAME")
	private String moduleName;
    /**
     * 资源名称
     */
	@TableField("RESOURCE_NAME")
	private String resourceName;
    /**
     * 操作名称
     */
	@TableField("OPRATE_NAME")
	private String oprateName;
    /**
     * 操作级别
     */
	@TableField("OPRATE_LEVEL")
	private String oprateLevel;
    /**
     * 机构编码
     */
	@TableField("AGENCY_CODE")
	private String agencyCode;
    /**
     * 请求地址
     */
	@TableField("REQ_URL")
	private String reqUrl;
    /**
     * 请求方式get,post,put,delete
     */
	@TableField("REQ_METHOD")
	private String reqMethod;



	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getOprateCode() {
		return oprateCode;
	}

	public void setOprateCode(String oprateCode) {
		this.oprateCode = oprateCode;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getOprateName() {
		return oprateName;
	}

	public void setOprateName(String oprateName) {
		this.oprateName = oprateName;
	}

	public String getOprateLevel() {
		return oprateLevel;
	}

	public void setOprateLevel(String oprateLevel) {
		this.oprateLevel = oprateLevel;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}



}