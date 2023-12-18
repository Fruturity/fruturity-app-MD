package com.ardine.fruturity.data.response

data class Fruits(
    val id: String,
    val imageUrl: String,
    val notes: String,
    val ripeness: String,
    val isBookmark: Boolean,
    val date: Date,
    val category: String
)