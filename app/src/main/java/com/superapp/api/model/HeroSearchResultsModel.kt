package com.superapp.api.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: XNGEL
 * @Date: 05/10/21
 */
class HeroSearchResultsModel {

    @SerializedName("response")
    val response: String? = null

    @SerializedName("results-for")
    val resultsFor: String? = null

    @SerializedName("results")
    val results: List<HeroModel>? = null

}