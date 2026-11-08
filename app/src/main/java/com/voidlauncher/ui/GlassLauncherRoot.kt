package com.voidlauncher.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.voidlauncher.data.Prefs
import com.voidlauncher.data.SampleApps
import com.voidlauncher.iconpack.IconPackManager
import com.voidlauncher.ui.drawer.AppDrawer
import com.voidlauncher.ui.home.HomeScreen
import com.voidlauncher.ui.settings.SettingsScreen
import com.voidlauncher.ui.widgets.WidgetsScreen
import kotlinx.coroutines.launch

@Composable
fun GlassLauncherRoot() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    var drawerOpen by remember { mutableStateOf(false) }
    var settingsOpen by remember { mutableStateOf(false) }
    var hidden by remember { mutableStateOf(SampleApps.all.filter { it.hidden }.map { it.packageName }.toSet()) }
    val context = LocalContext.current
    val iconPacks = remember { IconPackManager.listInstalled(context) }
    val selectedPack by Prefs.selectedPack(context).collectAsState(initial = null)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            when (page) {
                0 -> HomeScreen(
                    onOpenDrawer = { drawerOpen = true },
                    onOpenSettings = { settingsOpen = true }
                )
                1 -> WidgetsScreen()
            }
        }
        if (drawerOpen) {
            AppDrawer(
                apps = SampleApps.all,
                hidden = hidden,
                selectedPack = selectedPack,
                onToggleHidden = { pkg ->
                    hidden = if (hidden.contains(pkg)) hidden - pkg else hidden + pkg
                },
                onDismiss = { drawerOpen = false }
            )
        }
        if (settingsOpen) {
            SettingsScreen(
                iconPacks = iconPacks,
                selectedPack = selectedPack,
                onSelectPack = { scope.launch { Prefs.setSelectedPack(context, it) } },
                onDismiss = { settingsOpen = false }
            )
        }
    }
}
