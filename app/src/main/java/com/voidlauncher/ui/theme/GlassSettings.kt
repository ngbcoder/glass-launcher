package com.voidlauncher.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalGlassBlur = compositionLocalOf<Dp> { 22.dp }
val LocalGlassOpacity = compositionLocalOf<Float> { 0.07f }

val AccentPaletteList = listOf(AccentViolet, AccentCyan, AccentMagenta, AccentGreen)

fun accentForIndex(i: Int): Color = AccentPaletteList.getOrElse(i) { AccentViolet }
