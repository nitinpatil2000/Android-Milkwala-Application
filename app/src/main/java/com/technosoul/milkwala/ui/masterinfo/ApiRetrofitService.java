package com.technosoul.milkwala.ui.masterinfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofitService {
     private Retrofit retrofit;

        public ApiRetrofitService() {
            initializeRetrofit();
        }

        private void initializeRetrofit() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()

//                    .baseUrl("http://192.168.43.158:8080")
                    .baseUrl("http://192.168.182.3:8080")
//                    .baseUrl("http://192.168.194.3:8080")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        public Retrofit getRetrofit() {
            return retrofit;
        }
}
