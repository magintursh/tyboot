package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LocationInfo.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfo.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class LocationInfoModel extends BaseModel
{

	private static final long serialVersionUID = 8978734890730924L;

	private String pinyinName;



	private String locationName;

	private String locationCode;


	private String parentCode;

	private Integer locationLevel;

	private Integer orderNum;


	/**
	 * 经度
	 */
	private String longitude;

	//维度

	private String latitude;

	public String getPinyinName() {
		return this.pinyinName;
	}
	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}
	

	public String getLocationName() {
		return this.locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	

	public String getLocationCode() {
		return this.locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	


	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	

	public Integer getOrderNum() {
		return this.orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public Integer getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(Integer locationLevel) {
		this.locationLevel = locationLevel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}

