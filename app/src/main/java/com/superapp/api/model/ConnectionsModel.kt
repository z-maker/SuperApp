package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class ConnectionsModel(

	@SerializedName("relatives")
	val relatives: String? = null,

	@SerializedName("group-affiliation")
	val groupAffiliation: String? = null
)
