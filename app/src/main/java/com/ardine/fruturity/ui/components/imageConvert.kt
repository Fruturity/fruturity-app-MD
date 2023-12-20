package com.ardine.fruturity.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
fun imageConvert (
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val painter = rememberImagePainter(data = imageUrl)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}