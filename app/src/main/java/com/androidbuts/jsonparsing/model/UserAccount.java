package com.androidbuts.jsonparsing.model;


public class UserAccount {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public UserAccount(
            String login,
            String name,
            String lastName,
            String email,
            String password,
            String confirmPassword){
        this.login = login;
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }

}
