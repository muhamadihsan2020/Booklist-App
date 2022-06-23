package com.group4.booklistapp.booklist.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {

    private static final String baseURL = "http://172.20.0.1/booklist/";
    private static Retrofit retro;

    public static Retrofit con(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retro;
    }

}
