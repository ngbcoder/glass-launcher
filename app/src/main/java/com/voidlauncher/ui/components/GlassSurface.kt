package com.voidlauncher.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.voidlauncher.ui.theme.GlassEdge
import com.voidlauncher.ui.theme.GlassHi

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    blur: Dp = 22.dp,
    opacity: Float = 0.07f,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = Color.White.copy(alpha = opacity),
        border = BorderStroke(1.dp, GlassEdge),
        tonalElevation = 0.dp,
        shadowElevation = 8.dp
    ) {
        content()
    }
}
