package com.pratik.demoapp.utils

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    companion object {
        var retrofit: Retrofit? = null

        fun getRetrofitClient(): ApiInterface? {
            val logging = HttpLoggingInterceptor()
            logging.level=(HttpLoggingInterceptor.Level.BODY);
            val httpClient =  OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            retrofit = Retrofit.Builder()
                .baseUrl(DOMAIN_URL_KEY)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit?.create(ApiInterface::class.java)
        }
    }
}