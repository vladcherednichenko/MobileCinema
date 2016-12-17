package com.androidbuts.jsonparsing.model.user;

/**
 * Created by MarvellousChester on 13.12.2016.
 */

public class User {

    private static LoginData loginData;
    private static AllUserData allData;
    private static User user;
    private User(){}

    public static User getUser(){
        if(user == null)
            user = new User();
        return user;
    }

    public static void setLoginData(LoginData ld){
        loginData = ld;
    }
    public static void setAllData(AllUserData ad){
        allData = ad;
    }


}
