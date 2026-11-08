package com.voidlauncher.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voidlauncher.data.SampleApps
import com.voidlauncher.ui.drawer.AppDrawer
import com.voidlauncher.ui.home.HomeScreen
import com.voidlauncher.ui.widgets.WidgetsScreen

@Composable
fun GlassLauncherRoot() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    var drawerOpen by remember { mutableStateOf(false) }
    var hidden by remember { mutableStateOf(SampleApps.all.filter { it.hidden }.map { it.packageName }.toSet()) }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            when (page) {
                0 -> HomeScreen(onOpenDrawer = { drawerOpen = true })
                1 -> WidgetsScreen()
            }
        }
        if (drawerOpen) {
            AppDrawer(
                apps = SampleApps.all,
                hidden = hidden,
                onToggleHidden = { pkg ->
                    hidden = if (hidden.contains(pkg)) hidden - pkg else hidden + pkg
                },
                onDismiss = { drawerOpen = false }
            )
        }
    }
}
