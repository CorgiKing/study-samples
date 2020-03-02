package org.goaler.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "goaler.config")
public class AutoconfigureProperties {

    private String user;

    private String pwd;

    private Boolean showPwd = false;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getShowPwd() {
        return showPwd;
    }

    public void setShowPwd(Boolean showPwd) {
        this.showPwd = showPwd;
    }
}
