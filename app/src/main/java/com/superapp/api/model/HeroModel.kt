package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class HeroModel(

	@field:SerializedName("image")
	val image: ImageModel? = null,

	@field:SerializedName("appearance")
	val appearance: AppearanceModel? = null,

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("work")
	val work: WorkModel? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("powerstats")
	val powerStats: PowerStatsModel? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("biography")
	val biography: BiographyModel? = null,

	@field:SerializedName("connections")
	val connections: ConnectionsModel? = null
)


