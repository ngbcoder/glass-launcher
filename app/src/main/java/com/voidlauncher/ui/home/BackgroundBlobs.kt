package com.voidlauncher.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BackgroundBlobs(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "blobs")
    val t1 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(19000, easing = LinearEasing), RepeatMode.Reverse), label = "b1")
    val t2 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(23000, easing = LinearEasing), RepeatMode.Reverse), label = "b2")
    val t3 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(27000, easing = LinearEasing), RepeatMode.Reverse), label = "b3")

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        // blob 1
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF8B7CFF).copy(alpha = 0.35f), Color.Transparent),
                center = Offset(w * (0.2f + 0.1f * t1), h * (0.2f + 0.1f * t2)),
                radius = w * 0.6f
            ),
            center = Offset(w * (0.2f + 0.1f * t1), h * (0.2f + 0.1f * t2)),
            radius = w * 0.6f
        )
        // blob 2
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF4CC9F0).copy(alpha = 0.25f), Color.Transparent),
                center = Offset(w * (0.8f - 0.1f * t2), h * (0.7f - 0.1f * t1)),
                radius = w * 0.5f
            ),
            center = Offset(w * (0.8f - 0.1f * t2), h * (0.7f - 0.1f * t1)),
            radius = w * 0.5f
        )
        // blob 3
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFF72585).copy(alpha = 0.2f), Color.Transparent),
                center = Offset(w * (0.5f + 0.15f * t3), h * (0.5f - 0.1f * t3)),
                radius = w * 0.55f
            ),
            center = Offset(w * (0.5f + 0.15f * t3), h * (0.5f - 0.1f * t3)),
            radius = w * 0.55f
        )
    }
}
