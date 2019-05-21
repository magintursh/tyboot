package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */
@TableName("systemctl_operation_record")
public class OperationRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("OPERATION_TYPE")
	private String operationType;




	
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
	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("USER_ID")
	private String userId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}


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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
