package com.androidbuts.jsonparsing.retrofit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {


    //base URL
    private static final String ROOT_URL = "http://10.0.2.2";
    //private static final String ROOT_URL = "http://192.168.129.74:56666";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static API getApiService() {
        return getRetrofitInstance().create(API.class);
    }
}
