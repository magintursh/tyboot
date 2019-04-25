package org.typroject.tyboot.core.auth.face.model;

import java.io.Serializable;

public class AuthModel implements Serializable {


    private static final long serialVersionUID = 326567545234532L;

    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
