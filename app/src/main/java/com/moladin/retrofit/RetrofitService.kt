package com.moladin.com.moladin.retrofit

import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @GET("{endPoint}")
    suspend fun fetchData(
        @Path("endPoint") endPoint: String,
        @QueryMap queryMap: Map<String, String> = HashMap(),
        @HeaderMap token: Map<String, String> = HashMap()
    ): Response<String>

    @POST("{endPoint}")
    suspend fun fetchData(
        @Path("endPoint") endPoint: String,
        @Body body: String? = null,
        @HeaderMap token: Map<String, String> = HashMap(),
        @QueryMap queryMap: Map<String, String> = HashMap()
    ): Response<String>

}