package com.voidlauncher.data

import androidx.compose.ui.graphics.Color

data class AppInfo(
    val name: String,
    val packageName: String,
    val tint: Color,
    val hidden: Boolean = false
)
