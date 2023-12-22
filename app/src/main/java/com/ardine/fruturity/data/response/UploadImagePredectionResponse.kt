package com.ardine.fruturity.data.response

import com.google.gson.annotations.SerializedName

data class UploadImagePredectionResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Data(

	@field:SerializedName("fruit_types_prediction")
	val fruitTypesPrediction: String? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
