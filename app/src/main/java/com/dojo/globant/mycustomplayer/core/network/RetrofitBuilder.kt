package com.dojo.globant.mycustomplayer.core.network

import com.dojo.globant.mycustomplayer.common.Constants.BASE_URL
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {
    private val loggIn =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val keyInterceptor: Interceptor = Interceptor { chain ->
        val url = chain.request().url
            .newBuilder()
            .build()
        val request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    private fun createClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return OkHttpClient().newBuilder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(loggIn)
            .addInterceptor(keyInterceptor)
            .build()
    }

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createClient())
            .build()
    }

    companion object {
        private const val TIME_OUT = 60L
    }
}