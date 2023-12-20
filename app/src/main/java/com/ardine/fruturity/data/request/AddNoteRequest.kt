package com.ardine.fruturity.data.request

import com.google.gson.annotations.SerializedName

data class AddNoteRequest(
    @SerializedName("note") val note: String
)