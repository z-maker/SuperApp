package com.superapp.api.model

import com.google.gson.annotations.SerializedName

data class WorkModel(

	@SerializedName("occupation")
	val occupation: String? = null,

	@SerializedName("base")
	val base: String? = null
)
