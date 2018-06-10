package com.andrew.paginglibtest.di.module

import com.andrew.paginglibtest.data.network.ApiKeyInterceptor
import com.andrew.paginglibtest.data.network.BASE_API_ROUTE
import com.andrew.paginglibtest.data.network.MoviesApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Andrew on 03.06.2018.
 */

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor(loggingInterceptor)
            addInterceptor(apiKeyInterceptor)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideApi(httpClient: OkHttpClient): MoviesApi {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                .client(httpClient)
                .baseUrl(BASE_API_ROUTE)
                .build()
                .create(MoviesApi::class.java)
    }
}