package com.voidlauncher.iconpack

import android.content.Context
import android.content.pm.PackageManager

object IconPackManager {
    fun listInstalled(context: Context): List<IconPack> {
        val pm = context.packageManager
        val result = mutableListOf<IconPack>()
        val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
        for (pkg in packages) {
            val appInfo = pkg.applicationInfo ?: continue
            val label = pm.getApplicationLabel(appInfo).toString()
            val lower = label.lowercase() + " " + pkg.packageName.lowercase()
            if (lower.contains("icon") && (lower.contains("pack") || lower.contains("theme"))) {
                result.add(IconPack(pkg.packageName, label))
            }
        }
        // Always offer "Default" (no pack)
        return result
    }

    fun iconFor(context: Context, packPackage: String?, appPackageName: String): android.graphics.drawable.Drawable? {
        if (packPackage == null) return null
        return try {
            val res = context.packageManager.getResourcesForApplication(packPackage)
            val id = res.getIdentifier(appPackageName, "drawable", packPackage)
            if (id != 0) res.getDrawable(id, context.theme) else null
        } catch (e: Exception) {
            null
        }
    }
}
