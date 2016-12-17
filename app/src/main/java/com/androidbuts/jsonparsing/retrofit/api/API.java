package com.androidbuts.jsonparsing.retrofit.api;


import com.androidbuts.jsonparsing.model.FilmSessionList;
import com.androidbuts.jsonparsing.model.RegistrationResponse;
import com.androidbuts.jsonparsing.model.user.AllUserData;
import com.androidbuts.jsonparsing.model.user.LoginData;
import com.androidbuts.jsonparsing.model.UserAccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @POST("/Account/register")
    Call<RegistrationResponse> registerUser(@Body UserAccount userAccount);

    @POST("/token")
    Call<AllUserData> singUserIn(@Body LoginData loginData);

    //downloading json
    @GET("/jsonTest.json")
    //@GET("/api/getAll/02-12-2016")
    Call<FilmSessionList> getMyJSON();
}
