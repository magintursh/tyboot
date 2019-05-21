package org.typroject.tyboot.api.face.systemctl.orm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.typroject.tyboot.core.rdbms.orm.entity.BaseEntity;

/**
 * <p>
 * 用户反馈
 * </p>
 *
 * @author 子杨
 * @since 2017-12-01
 */
@TableName("systemctl_feedback")
public class Feedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("CONTENT")
	private String content;
    /**
     * 联系方式
     */
	@TableField("CONTACT")
	private String contact;

	@TableField("USER_ID")
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
