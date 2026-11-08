package com.voidlauncher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.data.SearchLogic
import com.voidlauncher.data.SearchResult

@Composable
fun HomeScreen(
    gestureMode: Int = 0,
    selectedPack: String? = null,
    onOpenDrawer: () -> Unit = {},
    onOpenSettings: () -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    val results = remember(query) { SearchLogic.resultsFor(query) }
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(gestureMode) {
            detectDragGestures { change, dragAmount ->
                if (gestureMode == 0 && dragAmount.y < -40f) { onOpenDrawer(); change.consume() }
                if (gestureMode == 1 && dragAmount.x < -40f) { onOpenDrawer(); change.consume() }
            }
        }) {
        BackgroundBlobs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = onOpenSettings) { Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color.White.copy(alpha = 0.7f)) }
            }
            StatusBar()
            Spacer(Modifier.height(8.dp))
            // Page indicator dots
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("•", color = Color.White, fontSize = 16.sp)
                Text("  ○", color = Color.White.copy(alpha = 0.4f), fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
            ClockCard()
            Spacer(Modifier.height(12.dp))
            NowPlayingCard()
            Spacer(Modifier.weight(1f))
            Text(
                "Swipe up for apps",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterHorizontally).clickable { onOpenDrawer() }
            )
            Spacer(Modifier.height(8.dp))
            if (query.isNotEmpty()) {
                SearchResultsOverlay(results = results, onResultClick = { /* handle */ })
                Spacer(Modifier.height(8.dp))
            }
            SearchBar(
                value = query,
                onValueChange = { query = it },
                onEnter = { /* select first result */ },
                onOpenDrawer = onOpenDrawer
            )
            Spacer(Modifier.height(12.dp))
            Dock(selectedPack = selectedPack, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(Modifier.height(8.dp))
            // persistent handle button
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(60.dp)
                    .height(6.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(3.dp))
                    .background(Color.White.copy(alpha = 0.4f))
                    .clickable { onOpenDrawer() }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
