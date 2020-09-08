package com.kang6264.daumbooksearch.di

import com.kang6264.daumbooksearch.BuildConfig
import com.kang6264.daumbooksearch.data.api.DaumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideBookSearchService(okHttpClient: OkHttpClient) : DaumApi{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.daumApiServer)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DaumApi::class.java)
    }

    @Provides
    fun provideRequestHeaderInterceptor() : Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().run {
                addHeader("Content-Type", "application/json")
                addHeader("Authorization", "KakaoAK ${BuildConfig.daumApiKey}")
                build()
            })
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(interceptor)
        }.build()
    }
}