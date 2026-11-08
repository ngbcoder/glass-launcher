package com.voidlauncher.ui.home

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.voidlauncher.iconpack.IconPackManager
import com.voidlauncher.ui.components.GlassSurface

private data class DockItem(val label: String, val packageName: String, val color: Color)

@Composable
fun Dock(selectedPack: String? = null, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val items = listOf(
        DockItem("Phone", "com.phone", Color(0xFF7AE582)),
        DockItem("Chrome", "com.chrome", Color(0xFF4CC9F0)),
        DockItem("Camera", "com.camera", Color(0xFFFFB347)),
        DockItem("Social", "com.social", Color(0xFF8B7CFF))
    )
    GlassSurface(modifier = modifier, shape = RoundedCornerShape(32.dp), opacity = 0.10f) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val icon = remember(item.packageName, selectedPack) {
                    IconPackManager.iconFor(context, selectedPack, item.packageName)
                }
                if (icon != null) {
                    Image(
                        bitmap = icon.toBitmap().asImageBitmap(),
                        contentDescription = item.label,
                        modifier = Modifier.size(52.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(androidx.compose.ui.graphics.Brush.linearGradient(listOf(item.color, item.color.copy(alpha = 0.6f)))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item.label.take(1), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
