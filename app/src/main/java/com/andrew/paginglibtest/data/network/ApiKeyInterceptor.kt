package com.andrew.paginglibtest.data.network

import com.andrew.paginglibtest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Andrew on 03.06.2018.
 */

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(FIELD_API_KEY, BuildConfig.TMDBApiKeyV3).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}