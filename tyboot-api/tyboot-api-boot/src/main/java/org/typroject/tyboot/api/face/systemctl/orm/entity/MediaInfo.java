package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

/**
 * <p>
 * 多媒体信息关联
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */
@TableName("systemctl_media_info")
public class MediaInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联对象类型(用户头像，帖子，帖子评论，帖子语音....）
     */
	@TableField("ENTITY_TYPE")
	private String entityType;
    /**
     * 关联实体id
     */
	@TableField("ENTITY_ID")
	private String entityId;
    /**
     * 媒体类型(图片，视频，音频...）
     */
	@TableField("MEDIA_TYPE")
	private String mediaType;
    /**
     * 媒体文件名称
     */
	@TableField("MEDIA_FILENAME")
	private String mediaFilename;
    /**
     * 媒体文件链接地址
     */
	@TableField("MEDIA_URL")
	private String mediaUrl;
    /**
     * 媒体别名
     */
	@TableField("MEDIA_ALIAS")
	private String mediaAlias;
	@TableField("ORDER_NUM")
	private Integer orderNum;


	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaFilename() {
		return mediaFilename;
	}

	public void setMediaFilename(String mediaFilename) {
		this.mediaFilename = mediaFilename;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getMediaAlias() {
		return mediaAlias;
	}

	public void setMediaAlias(String mediaAlias) {
		this.mediaAlias = mediaAlias;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


}
