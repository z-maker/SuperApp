package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class PowerStatsModel(

	@field:SerializedName("strength")
	val strength: String? = "0",

	@field:SerializedName("durability")
	val durability: String? = "0",

	@field:SerializedName("combat")
	val combat: String? = "0",

	@field:SerializedName("power")
	val power: String? = "0",

	@field:SerializedName("speed")
	val speed: String? = "0",

	@field:SerializedName("intelligence")
	val intelligence: String? = "0"
)
