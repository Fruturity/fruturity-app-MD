package com.ardine.fruturity.ui.screen.myStuff.bookmark

import com.ardine.fruturity.data.model.FruitHistory


data class BookmarkState(
    val fruitmarked: List<FruitHistory>,
    val isBookmark: Boolean,
)