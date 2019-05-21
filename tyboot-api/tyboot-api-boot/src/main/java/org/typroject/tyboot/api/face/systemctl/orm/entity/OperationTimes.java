package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 操作计数
 * </p>
 *
 * @author 子杨
 * @since 2018-12-26
 */
@TableName("systemctl_operation_times")
public class OperationTimes extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Long sequenceNbr;
    /**
     * 操作类型
     */
	@TableField("OPERATION_TYPE")
	private String operationType;
    /**
     * 操作计数
     */
	@TableField("OPERATION_TIMES")
	private int operationTimes;
    /**
     * 关联对象类型(商户，商品。。。.）
     */
	@TableField("ENTITY_TYPE")
	private String entityType;
    /**
     * 关联实体id
     */
	@TableField("ENTITY_ID")
	private String entityId;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;

	/**
	 * 操作用户
	 */
	@TableField("USER_ID")
	private String UserId;



	@Override
	public Long getSequenceNbr() {
		return sequenceNbr;
	}

	@Override
	public void setSequenceNbr(Long sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}


	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public int getOperationTimes() {
		return operationTimes;
	}

	public void setOperationTimes(int operationTimes) {
		this.operationTimes = operationTimes;
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
