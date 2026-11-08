package com.voidlauncher.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.data.SearchResult
import com.voidlauncher.ui.components.GlassSurface

@Composable
fun SearchResultsOverlay(
    results: List<SearchResult>,
    onResultClick: (SearchResult) -> Unit,
    modifier: Modifier = Modifier
) {
    if (results.isEmpty()) return
    GlassSurface(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), opacity = 0.12f) {
        Column(modifier = Modifier.padding(8.dp)) {
            results.forEach { r ->
                val label = when (r) {
                    is SearchResult.App -> r.app.name
                    is SearchResult.Contact -> r.name
                    is SearchResult.Setting -> r.label
                    is SearchResult.Calc -> "${r.expression} = ${r.result}"
                    is SearchResult.Timer -> "Timer ${r.seconds}s"
                    is SearchResult.Web -> "Search web: ${r.query}"
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onResultClick(r) }
                        .padding(12.dp)
                ) {
                    Text(label, color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}
