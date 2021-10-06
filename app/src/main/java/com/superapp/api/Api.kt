package com.superapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.Interceptor
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.Synchronized

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
object Api {

    private const val BASE_URL: String = "https://www.superheroapi.com/api.php/166568558974983/";
    private val client: OkHttpClient = buildClient();
    private val apiClient = buildApiClient()

    private fun buildClient():OkHttpClient{
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val req = chain.request()
                val url = req.url()
                val requestBuilder = req.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        return builder.build()
    }

    private fun buildApiClient(): Retrofit {
        val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @JvmStatic
    @Synchronized
    fun <T> createService(service: Class<T>?): T {
        val clientAuth = client.newBuilder().addInterceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val requestUrl = request.url()
            val url = requestUrl.newBuilder().build()
            val builder = request.newBuilder().url(url)
            request = builder.build()
            chain.proceed(request)
        }.build()
        val client = apiClient.newBuilder().client(clientAuth).build()
        return client.create(service)
    }

}