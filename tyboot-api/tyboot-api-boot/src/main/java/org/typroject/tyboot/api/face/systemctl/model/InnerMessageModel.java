package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 内部消息 model
 * </p>
 *
 * @author 子杨
 * @since 2018-12-12
 */
public class InnerMessageModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 目标用户id
     */
    private String targetUserId;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 消息类型：点赞,评论
     */
    private String messageType;

    private String userId;

    private String messageStatus;

    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

}
