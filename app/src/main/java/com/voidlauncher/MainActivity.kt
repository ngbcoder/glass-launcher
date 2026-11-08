package com.voidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.voidlauncher.ui.GlassLauncherRoot
import com.voidlauncher.ui.theme.GlassLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlassLauncherTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GlassLauncherRoot()
                }
            }
        }
    }
}
