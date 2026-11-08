package com.voidlauncher.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.glass(
    shape: Shape,
    blur: Dp = 22.dp,
    opacity: Float = 0.07f,
    edge: Color = GlassEdge,
    highlight: Color = GlassHi
): Modifier = this
    .blur(blur)
    .background(Color.White.copy(alpha = opacity), shape)
    .border(BorderStroke(1.dp, edge), shape)
    .clip(shape)
