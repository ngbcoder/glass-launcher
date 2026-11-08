package com.voidlauncher.data

import androidx.compose.ui.graphics.Color

object SampleApps {
    val all: List<AppInfo> = listOf(
        AppInfo("Browser", "com.browser", Color(0xFF4CC9F0)),
        AppInfo("Calculator", "com.calc", Color(0xFFFFB347)),
        AppInfo("Calendar", "com.calendar", Color(0xFF7AE582)),
        AppInfo("Camera", "com.camera", Color(0xFFFF6B6B)),
        AppInfo("Chrome", "com.chrome", Color(0xFF4CC9F0)),
        AppInfo("Clock", "com.clock", Color(0xFF8B7CFF)),
        AppInfo("Contacts", "com.contacts", Color(0xFF7AE582)),
        AppInfo("Drive", "com.drive", Color(0xFF4CC9F0)),
        AppInfo("Files", "com.files", Color(0xFFFFB347)),
        AppInfo("Gmail", "com.gmail", Color(0xFFF72585)),
        AppInfo("Maps", "com.maps", Color(0xFF7AE582)),
        AppInfo("Messages", "com.messages", Color(0xFF4CC9F0)),
        AppInfo("Music", "com.music", Color(0xFF8B7CFF)),
        AppInfo("Notes", "com.notes", Color(0xFFFFB347)),
        AppInfo("Phone", "com.phone", Color(0xFF7AE582)),
        AppInfo("Photos", "com.photos", Color(0xFFFF6B6B)),
        AppInfo("Settings", "com.settings", Color(0xFF9AA0A6)),
        AppInfo("Telegram", "com.telegram", Color(0xFF4CC9F0)),
        AppInfo("Wallet", "com.wallet", Color(0xFF8B7CFF), hidden = true),
        AppInfo("Weather", "com.weather", Color(0xFF4CC9F0)),
        AppInfo("YouTube", "com.youtube", Color(0xFFF72585)),
        AppInfo("Zoom", "com.zoom", Color(0xFF4CC9F0))
    ).sortedBy { it.name }
}
