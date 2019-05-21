package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * model
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */
public class OperationRecordModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String operationType;
    /**
     * /**
     * 关联对象类型(用户头像，帖子，帖子评论，帖子语音....）
     */
    private String entityType;
    /**
     * 关联实体id
     */
    private String entityId;
    private Date createTime;


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
