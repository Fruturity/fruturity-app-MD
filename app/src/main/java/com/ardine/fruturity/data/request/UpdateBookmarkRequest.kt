package com.ardine.fruturity.data.request

import com.google.gson.annotations.SerializedName

data class UpdateBookmarkRequest(
    @SerializedName("bookmarkStatus") val bookmarkStatus: Boolean
)
