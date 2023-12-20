package com.ardine.fruturity.data.response

import com.google.gson.annotations.SerializedName

data class AddNoteResponse(
    @SerializedName("message") val message: String
)