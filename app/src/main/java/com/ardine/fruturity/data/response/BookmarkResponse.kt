package com.ardine.fruturity.data.response

import com.google.gson.annotations.SerializedName

data class BookmarkResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: FruitResponse
)