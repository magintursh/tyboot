package org.typroject.tyboot.component.notification.push;

import java.io.Serializable;

/**
 * Created by yaohelang on 2017/10/1.
 */
public class PushTemplate implements Serializable {


    private Platform platform;

    private String [] target;

    private String content;

    private String title;


    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String [] getTarget() {
        return target;
    }

    public void setTarget(String [] target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
