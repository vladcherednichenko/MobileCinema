package com.androidbuts.jsonparsing.retrofit.api;

import com.androidbuts.jsonparsing.model.ContactList;
import com.androidbuts.jsonparsing.model.FilmSessionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    //@GET("/api/values/getAll")

    @GET("/jsonTest.json")

    Call<FilmSessionList> getMyJSON();

    //Call<MovieList> getMyJSON();
}
