package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 操作计数 model
 * </p>
 *
 * @author 子杨
 * @since 2018-12-26
 */
public class OperationTimesModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 操作计数
     */
    private int operationTimes;
    /**
     * 关联对象类型(商户，商品。。。.）
     */
    private String entityType;
    /**
     * 关联实体id
     */
    private String entityId;
    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 操作用户
     */
    private String UserId;


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
