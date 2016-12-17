package com.androidbuts.jsonparsing.model.user;

public class LoginData {
    private String login;
    private String password;
    private String grant_type;

    public LoginData(String login, String password, String grant_type) {
        this.login = login;
        this.password = password;
        this.grant_type = grant_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
