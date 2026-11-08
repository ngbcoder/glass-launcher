package com.voidlauncher.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.voidlauncher.ui.theme.LocalAccent
import com.voidlauncher.ui.theme.LocalGlassBlur
import com.voidlauncher.ui.theme.LocalGlassOpacity
import com.voidlauncher.ui.theme.accentForIndex
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

    // settings state (live preview)
    var blur by remember { mutableStateOf(22f) }
    var opacity by remember { mutableStateOf(0.07f) }
    var accentIndex by remember { mutableStateOf(0) }
    var gestureMode by remember { mutableStateOf(0) }

    // collect persisted values
    val blurFlow by Prefs.blur(context).collectAsState(initial = 22f)
    val opacityFlow by Prefs.opacity(context).collectAsState(initial = 0.07f)
    val accentFlow by Prefs.accentIndex(context).collectAsState(initial = 0)
    val gestureFlow by Prefs.gestureMode(context).collectAsState(initial = 0)
    val selectedPack by Prefs.selectedPack(context).collectAsState(initial = null)

    // sync flows -> state on first emission handled by initial; keep state in sync
    LaunchedEffect(blurFlow) { blur = blurFlow }
    LaunchedEffect(opacityFlow) { opacity = opacityFlow }
    LaunchedEffect(accentFlow) { accentIndex = accentFlow }
    LaunchedEffect(gestureFlow) { gestureMode = gestureFlow }

    val scope = rememberCoroutineScope()
    val accent = accentForIndex(accentIndex)

    androidx.compose.runtime.CompositionLocalProvider(
        LocalGlassBlur provides androidx.compose.ui.unit.Dp(blur),
        LocalGlassOpacity provides opacity,
        LocalAccent provides com.voidlauncher.ui.theme.AccentPalette(accent)
    ) {
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
                    blur = blur,
                    opacity = opacity,
                    accentIndex = accentIndex,
                    gestureMode = gestureMode,
                    onBlurChange = { v -> blur = v; scope.launch { Prefs.setBlur(context, v) } },
                    onOpacityChange = { v -> opacity = v; scope.launch { Prefs.setOpacity(context, v) } },
                    onAccentChange = { i -> accentIndex = i; scope.launch { Prefs.setAccentIndex(context, i) } },
                    onGestureChange = { i -> gestureMode = i; scope.launch { Prefs.setGestureMode(context, i) } },
                    onDismiss = { settingsOpen = false }
                )
            }
        }
    }
}
