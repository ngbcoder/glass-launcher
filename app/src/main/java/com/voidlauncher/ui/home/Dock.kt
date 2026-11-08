package com.voidlauncher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.ui.components.GlassSurface

private data class DockItem(val label: String, val color: Color)

@Composable
fun Dock(modifier: Modifier = Modifier) {
    val items = listOf(
        DockItem("Phone", Color(0xFF7AE582)),
        DockItem("Chrome", Color(0xFF4CC9F0)),
        DockItem("Camera", Color(0xFFFFB347)),
        DockItem("Social", Color(0xFF8B7CFF))
    )
    GlassSurface(modifier = modifier, shape = RoundedCornerShape(32.dp), opacity = 0.10f) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Brush.linearGradient(listOf(item.color, item.color.copy(alpha = 0.6f)))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.label.take(1), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
