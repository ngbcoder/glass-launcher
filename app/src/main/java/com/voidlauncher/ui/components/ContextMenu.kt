package com.voidlauncher.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.data.AppInfo

@Composable
fun ContextMenu(
    app: AppInfo,
    hidden: Boolean,
    onAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    GlassSurface(modifier = modifier.width(200.dp), shape = RoundedCornerShape(16.dp), opacity = 0.2f) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(app.name, color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold, modifier = Modifier.padding(8.dp))
            val actions = listOf(
                "Pin to dock",
                "Add to folder",
                if (hidden) "Unhide" else "Hide",
                "App info"
            )
            actions.forEach { a ->
                Row(modifier = Modifier.fillMaxWidth().clickable { onAction(a) }.padding(8.dp)) {
                    Text(a, color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}
