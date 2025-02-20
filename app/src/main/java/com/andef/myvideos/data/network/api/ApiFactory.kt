package com.andef.myvideos.data.network.api

import com.andef.myvideos.di.annotation.ApplicationScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ApplicationScope
object ApiFactory {
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}