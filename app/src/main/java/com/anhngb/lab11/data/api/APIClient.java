package com.anhngb.lab11.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://68485d1bec44b9f34940afe6.mockapi.io";
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
