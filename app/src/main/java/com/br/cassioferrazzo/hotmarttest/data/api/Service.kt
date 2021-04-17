package com.br.cassioferrazzo.hotmarttest.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Service {

    private val baseURL = "https://hotmart-mobile-app.herokuapp.com"

    fun <T> createService(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(bodyLogInterceptor())
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun bodyLogInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

}