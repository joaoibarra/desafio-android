package com.joaoibarra.gitapp.model.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.joaoibarra.gitapp.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitApiService {

    private val retrofit: Retrofit by lazy {
        val gsonBuilder = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(gsonBuilder))
                .baseUrl(Constants.API)
                .client(builder.build())
                .build()
    }

    private val builder = OkHttpClient.Builder()

    val gitApi: GitApi by lazy {
        retrofit.create(GitApi::class.java)
    }
}