package com.ardine.fruturity.data

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)