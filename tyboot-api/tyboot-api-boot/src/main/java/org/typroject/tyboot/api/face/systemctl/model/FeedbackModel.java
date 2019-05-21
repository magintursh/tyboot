package org.typroject.tyboot.api.face.systemctl.model;

import org.typroject.tyboot.core.rdbms.model.BaseModel;

/**
 * <p>
 * 用户反馈 model
 * </p>
 *
 * @author 子杨
 * @since 2017-12-01
 */
public class FeedbackModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String content;
    /**
     * 联系方式
     */
    private String contact;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
