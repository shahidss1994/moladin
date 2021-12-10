package com.moladin.com.moladin.retrofit

import com.moladin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private var BASE_URL = if (BuildConfig.DEBUG) {
        "https://gh-trending-api.herokuapp.com/"
    } else {
        "https://gh-trending-api.herokuapp.com/"
    }

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(makeOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .addNetworkInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("timestamp", (System.currentTimeMillis() + (1 * 1000)).toString())
                requestBuilder.header("Content-Type", "application/json; charset=utf-8")
                requestBuilder.header("Accept", "application/json")
                chain.proceed(requestBuilder.build())
            }
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

}