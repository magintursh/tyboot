package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author magintursh
 * @since 2017-06-27
 */
public class DictionarieModel extends BaseModel {

    private static final long serialVersionUID = 57438927329847L;

    /**
     * 字典编码 系统中固定不变
     */
	private String dictCode;
    /**
     * 字典名字
     */
	private String dictName;
    /**
     * 字典别名
     */
	private String dictAlias;
    /**
     * 字典值描述
     */
	private String dictDesc;
    /**
     * 业务类型
     */
	private String buType;
    /**
     * 机构编码
     */
	private String agencyCode;


	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictAlias() {
		return dictAlias;
	}

	public void setDictAlias(String dictAlias) {
		this.dictAlias = dictAlias;
	}

	public String getDictDesc() {
		return dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public String getBuType() {
		return buType;
	}

	public void setBuType(String buType) {
		this.buType = buType;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}



}
