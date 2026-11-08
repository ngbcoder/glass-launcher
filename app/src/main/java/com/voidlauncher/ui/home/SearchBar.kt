package com.voidlauncher.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voidlauncher.ui.components.GlassSurface

@Composable
fun SearchBar(modifier: Modifier = Modifier, onOpenDrawer: () -> Unit = {}) {
    var query by remember { mutableStateOf("") }
    GlassSurface(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(99.dp), opacity = 0.10f) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.White.copy(alpha = 0.7f))
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { if (it.isFocused) onOpenDrawer() },
                placeholder = { Text("Search apps, contacts, web…", color = Color.White.copy(alpha = 0.5f)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
        }
    }
}
