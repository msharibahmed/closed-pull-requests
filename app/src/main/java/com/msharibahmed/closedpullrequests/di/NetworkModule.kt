package com.msharibahmed.closedpullrequests.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msharibahmed.closedpullrequests.network.ClosedPrApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideClosedPrService(retrofit: Retrofit.Builder): ClosedPrApi {
        return retrofit
            .build()
            .create(ClosedPrApi::class.java)
    }
}