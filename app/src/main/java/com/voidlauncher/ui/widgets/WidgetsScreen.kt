package com.voidlauncher.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.ui.components.GlassSurface
import com.voidlauncher.ui.home.BackgroundBlobs

@Composable
fun WidgetsScreen() {
    androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize()) {
        BackgroundBlobs()
        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Text("Today", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(12.dp))
            GlassSurface(modifier = Modifier.fillMaxWidth(), shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Weather", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text("22° Sunny", color = Color.White.copy(alpha = 0.7f))
                }
            }
            Spacer(Modifier.height(12.dp))
            GlassSurface(modifier = Modifier.fillMaxWidth(), shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Calendar", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text("10:30 Standup", color = Color.White.copy(alpha = 0.7f))
                }
            }
            Spacer(Modifier.height(12.dp))
            GlassSurface(modifier = Modifier.fillMaxWidth(), shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Battery", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text("87% · 4h left", color = Color.White.copy(alpha = 0.7f))
                }
            }
            Spacer(Modifier.height(12.dp))
            GlassSurface(modifier = Modifier.fillMaxWidth(), shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Quick note", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text("Tap to edit", color = Color.White.copy(alpha = 0.7f))
                }
            }
        }
    }
}
