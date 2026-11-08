package com.voidlauncher.ui.drawer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.data.AppInfo
import com.voidlauncher.ui.components.GlassSurface
import com.voidlauncher.ui.home.BackgroundBlobs

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun AppDrawer(
    apps: List<AppInfo>,
    hidden: Set<String>,
    onToggleHidden: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val filtered = apps.filter { it.name.contains(query, ignoreCase = true) }
    // group by first letter
    val grouped = filtered.groupBy { it.name.first().uppercaseChar() }.toSortedMap()

    Box(modifier = modifier.fillMaxSize()) {
        // scrim
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.35f)).clickable { onDismiss() })
        GlassSurface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            opacity = 0.12f
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // handle bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.width(40.dp).height(4.dp).clip(RoundedCornerShape(2.dp)).background(Color.White.copy(alpha = 0.4f)))
                }
                // search
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Search apps", color = Color.White.copy(alpha = 0.5f)) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }
                // list
                LazyColumn(modifier = Modifier.weight(1f)) {
                    grouped.forEach { (letter, list) ->
                        stickyHeader {
                            Text(
                                letter.toString(),
                                color = Color.White.copy(alpha = 0.6f),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.2f))
                                    .padding(horizontal = 20.dp, vertical = 6.dp)
                            )
                        }
                        items(list, key = { it.packageName }) { app ->
                            val isHidden = hidden.contains(app.packageName) || app.hidden
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { /* launch */ }
                                    .padding(horizontal = 20.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(app.tint.copy(alpha = 0.8f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(app.name.take(1), color = Color.White, fontWeight = FontWeight.Bold)
                                }
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    app.name,
                                    color = if (isHidden) Color.White.copy(alpha = 0.4f) else Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = if (isHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = "toggle visibility",
                                    tint = Color.White.copy(alpha = 0.6f),
                                    modifier = Modifier.clickable { onToggleHidden(app.packageName) }
                                )
                            }
                        }
                    }
                }
                // bottom handle
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Close", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                }
            }
        }
    }
}
