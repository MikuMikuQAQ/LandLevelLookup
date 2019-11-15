package com.demo.pagingwithnetwork.data.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceCreator {

    private static String BASE_URL = "http://guolin.tech/";

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private Retrofit retrofit = builder.build();

    <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
