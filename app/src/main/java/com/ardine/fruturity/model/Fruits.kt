package com.ardine.fruturity.model

data class Fruits(
    val id: Long,
    val imageUrl: String, // This should be a Drawable resource ID, not an Int
    val title: String,
    val description: String,
    val ripeness: String,
    val isBookmark: Boolean,
    val date: String,
    val category: String
)