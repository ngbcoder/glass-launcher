package com.voidlauncher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AccentPalette(
    val accent: Color = AccentDefault
)

val LocalAccent = staticCompositionLocalOf { AccentPalette() }

private val DarkColors = darkColorScheme(
    primary = AccentDefault,
    background = BgDeep,
    surface = GlassBase,
    onPrimary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun GlassLauncherTheme(
    accent: Color = AccentDefault,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColors.copy(primary = accent)
    CompositionLocalProvider(LocalAccent provides AccentPalette(accent)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = GlassTypography,
            content = content
        )
    }
}
