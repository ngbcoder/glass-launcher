package com.voidlauncher.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.iconpack.IconPack
import com.voidlauncher.ui.components.GlassSurface
import com.voidlauncher.ui.theme.AccentPaletteList
import com.voidlauncher.ui.theme.accentForIndex

@Composable
fun SettingsScreen(
    iconPacks: List<IconPack>,
    selectedPack: String?,
    onSelectPack: (String?) -> Unit,
    blur: Float,
    opacity: Float,
    accentIndex: Int,
    gestureMode: Int,
    onBlurChange: (Float) -> Unit,
    onOpacityChange: (Float) -> Unit,
    onAccentChange: (Int) -> Unit,
    onGestureChange: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    GlassSurface(modifier = Modifier.fillMaxSize(), shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp), opacity = 0.95f) {
        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Settings", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                IconButton(onClick = onDismiss) { Icon(Icons.Filled.Close, contentDescription = "Close", tint = Color.White) }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                item {
                    Text("Glass blur: ${blur.toInt()} px", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Slider(value = blur, onValueChange = onBlurChange, valueRange = 6f..50f)
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    Text("Glass opacity: ${(opacity * 100).toInt()}%", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Slider(value = opacity, onValueChange = onOpacityChange, valueRange = 0.03f..0.25f)
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    Text("Accent color", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        AccentPaletteList.forEachIndexed { i, c ->
                            androidx.compose.material3.Surface(
                                modifier = Modifier
                                    .width(40.dp).height(40.dp)
                                    .clickable { onAccentChange(i) },
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
                                color = c,
                                border = if (i == accentIndex) androidx.compose.foundation.BorderStroke(2.dp, Color.White) else null
                            ) {}
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    Text("Drawer open method", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(4.dp))
                    val modes = listOf("Swipe up", "Swipe left", "Tap search", "Handle button")
                    modes.forEachIndexed { i, m ->
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable { onGestureChange(i) }.padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(m, color = Color.White, modifier = Modifier.weight(1f))
                            if (i == gestureMode) Text("✓", color = Color.White)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    Text("Icon Pack", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(4.dp))
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { onSelectPack(null) }.padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Default", color = Color.White, modifier = Modifier.weight(1f))
                        if (selectedPack == null) Text("✓", color = Color.White)
                    }
                }
                items(iconPacks) { pack ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { onSelectPack(pack.packageName) }.padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(pack.label, color = Color.White, modifier = Modifier.weight(1f))
                        if (selectedPack == pack.packageName) Text("✓", color = Color.White)
                    }
                }
            }
        }
    }
}
