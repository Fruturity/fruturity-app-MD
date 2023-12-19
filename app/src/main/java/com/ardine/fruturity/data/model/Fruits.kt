package com.ardine.fruturity.data.model

data class Fruits(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val description: String,
    val ripeness: String,
    val isBookmark: Boolean,
    val date: String,
    val category: String
)