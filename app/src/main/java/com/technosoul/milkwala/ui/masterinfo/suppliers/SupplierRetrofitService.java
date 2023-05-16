package com.technosoul.milkwala.ui.masterinfo.suppliers;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupplierRetrofitService {
     private Retrofit retrofit;

        public SupplierRetrofitService() {
            initializeRetrofilt();
        }

        private void initializeRetrofilt() {
            retrofit = new Retrofit.Builder()

                    .baseUrl("http://192.168.0.191:8080")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
        }

        public Retrofit getRetrofit() {
            return retrofit;
        }
}
