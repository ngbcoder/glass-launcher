package com.voidlauncher.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.ui.components.GlassSurface
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ClockCard(modifier: Modifier = Modifier) {
    val now = Date()
    val time = SimpleDateFormat("h:mm", Locale.getDefault()).format(now)
    val date = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(now)
    GlassSurface(modifier = modifier.fillMaxWidth(), shape = androidx.compose.foundation.shape.RoundedCornerShape(26.dp)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(time, color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Light, letterSpacing = (-1.5).sp)
            Text(date, color = Color.White.copy(alpha = 0.7f), fontSize = 15.sp)
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GlanceChip("Weather", "22°")
                GlanceChip("Next", "10:30 Standup")
                GlanceChip("Battery", "87%")
            }
        }
    }
}

@Composable
fun GlanceChip(label: String, value: String) {
    GlassSurface(shape = androidx.compose.foundation.shape.RoundedCornerShape(99.dp), opacity = 0.10f) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(value, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
