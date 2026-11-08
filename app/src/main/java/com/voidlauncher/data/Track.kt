package com.voidlauncher.data

import androidx.compose.ui.graphics.Color

data class Track(
    val title: String,
    val artist: String,
    val durationSec: Int,
    val gradient: Pair<Color, Color>
)
