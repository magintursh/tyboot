package org.typroject.tyboot.core.auth.face.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

/**
 * <p>
 * 资源操作表
 * </p>
 *
 * @author magintursh
 * @since 2017-08-18
 */
public class ResourceOperationModel extends BaseModel {

    private static final long serialVersionUID = 89784513154165465L;


    /**
     * 模块代码，程序中的注解
     */
	private String moduleCode;
    /**
     * 资源代码，程序中的注解
     */
	private String resourceCode;
    /**
     * 操作代码，程序中的注解
     */
	private String oprateCode;
    /**
     * 按约定规则识别操作的唯一的标识FXXXXXXXXXX(F后面8位数字加2位字符)
     */
	private String apiCode;
    /**
     * 父主键UID
     */
	private String parentId;
    /**
     * 资源类型(模块、资源、操作)
     */
	private String resType;
    /**
     * 操作级别代码，程序中的注解
     */
	private String levelCode;
    /**
     * 模块名称
     */
	private String moduleName;
    /**
     * 资源名称
     */
	private String resourceName;
    /**
     * 操作名称
     */
	private String oprateName;
    /**
     * 操作级别
     */
	private String oprateLevel;
    /**
     * 机构编码
     */
	private String agencyCode;
    /**
     * 请求地址
     */
	private String reqUrl;
    /**
     * 请求方式get,post,put,delete
     */
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
