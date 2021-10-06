package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class BiographyModel(

	@SerializedName("place-of-birth")
	val placeOfBirth: String? = null,

	@SerializedName("aliases")
	val aliases: List<String?>? = null,

	@SerializedName("first-appearance")
	val firstAppearance: String? = null,

	@SerializedName("publisher")
	val publisher: String? = null,

	@SerializedName("alignment")
	val alignment: String? = null,

	@SerializedName("full-name")
	val fullName: String? = null,

	@SerializedName("alter-egos")
	val alterEgos: String? = null
)
