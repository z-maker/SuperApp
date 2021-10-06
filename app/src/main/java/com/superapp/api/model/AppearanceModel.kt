package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class AppearanceModel(

	@SerializedName("eye-color")
	val eyeColor: String? = null,

	@SerializedName("gender")
	val gender: String? = null,

	@SerializedName("race")
	val race: String? = null,

	@SerializedName("weight")
	val weight: List<String?>? = null,

	@SerializedName("height")
	val height: List<String?>? = null,

	@SerializedName("hair-color")
	val hairColor: String? = null
)
