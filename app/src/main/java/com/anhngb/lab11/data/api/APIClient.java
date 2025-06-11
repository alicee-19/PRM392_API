package com.anhngb.lab11.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "http://10.0.2.2:5098";
    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                       .baseUrl(baseURL)
                       .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
