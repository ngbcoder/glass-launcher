package com.voidlauncher.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.ui.components.GlassSurface
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ToastHost(message: String?, onDismiss: () -> Unit) {
    if (message == null) return
    LaunchedEffect(message) {
        delay(1500)
        onDismiss()
    }
    Box(modifier = Modifier.padding(bottom = 80.dp), contentAlignment = Alignment.BottomCenter) {
        GlassSurface(shape = RoundedCornerShape(99.dp), opacity = 0.2f) {
            Text(message, color = Color.White, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
        }
    }
}
