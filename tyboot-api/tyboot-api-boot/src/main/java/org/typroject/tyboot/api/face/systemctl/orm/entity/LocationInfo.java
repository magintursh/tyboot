package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

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
@TableName("systemctl_location_info")
public class LocationInfo extends BaseEntity
{
	private static final long serialVersionUID = 4156498435169789L;

	@TableField("PINYIN_NAME")
	private String pinyinName;



	@TableField("LOCATION_NAME")
	private String locationName;

	@TableField("LOCATION_CODE")
	private String locationCode;


	@TableField("PARENT_CODE")
	private String parentCode;

	@TableField("LOCATION_LEVEL")
	private Integer locationLevel;

	@TableField( "ORDER_NUM")
	private Integer orderNum;


	/**
	 * 经度
	 */
	@TableField( "LONGITUDE")
	private String longitude;

	//维度

	@TableField( "LATITUDE")
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

