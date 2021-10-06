package com.superapp.api.network

import com.superapp.api.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
interface HeroService {

    @GET("/")
    suspend fun getAll():Response<List<HeroModel>>

    @GET("search/{search_key}")
    suspend fun search(@Path(value = "search_key", encoded = true) search: String ): Response<HeroSearchResultsModel>

    @GET("{hero_id}/")
    suspend fun getById(@Path(value = "hero_id", encoded = true) id: String ): Response<HeroModel>

    @GET("{hero_id}/powerstats")
    suspend fun getPowerStats(@Path(value = "hero_id", encoded = true) id: Int ): Response<PowerStatsModel>

    @GET("{hero_id}/biography")
    suspend fun getBiography(@Path(value = "hero_id", encoded = true) id: Int ): Response<BiographyModel>

    @GET("{hero_id}/appearance")
    suspend fun getAppearance(@Path(value = "hero_id", encoded = true) id: Int ): Response<AppearanceModel>

    @GET("{hero_id}/work")
    suspend fun getWork(@Path(value = "hero_id", encoded = true) id: Int ): Response<WorkModel>

    @GET("{hero_id}/connections")
    suspend fun getConnections(@Path(value = "hero_id", encoded = true) id: Int ): Response<ConnectionsModel>

}