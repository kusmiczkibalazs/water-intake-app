package com.example.assignmentapp.goalachieved.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.quotable.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuoteApiService {
    @GET("random?maxLength=50")
    suspend fun getQuote(): QuoteProperty
}

object QuoteApi {
    val retrofitService: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }
}