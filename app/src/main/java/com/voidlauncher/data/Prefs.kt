package com.voidlauncher.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "glass_launcher_prefs")
private val SELECTED_PACK = stringPreferencesKey("selected_icon_pack")
private val BLUR = floatPreferencesKey("glass_blur")
private val OPACITY = floatPreferencesKey("glass_opacity")
private val ACCENT = intPreferencesKey("accent_index")
private val GESTURE = intPreferencesKey("gesture_mode")

object Prefs {
    fun selectedPack(context: Context): Flow<String?> =
        context.dataStore.data.map { it[SELECTED_PACK] }

    suspend fun setSelectedPack(context: Context, value: String?) {
        context.dataStore.edit { prefs ->
            if (value == null) prefs.remove(SELECTED_PACK) else prefs[SELECTED_PACK] = value
        }
    }

    fun blur(context: Context): Flow<Float> = context.dataStore.data.map { it[BLUR] ?: 22f }
    fun opacity(context: Context): Flow<Float> = context.dataStore.data.map { it[OPACITY] ?: 0.07f }
    fun accentIndex(context: Context): Flow<Int> = context.dataStore.data.map { it[ACCENT] ?: 0 }
    fun gestureMode(context: Context): Flow<Int> = context.dataStore.data.map { it[GESTURE] ?: 0 }

    suspend fun setBlur(context: Context, v: Float) { context.dataStore.edit { it[BLUR] = v } }
    suspend fun setOpacity(context: Context, v: Float) { context.dataStore.edit { it[OPACITY] = v } }
    suspend fun setAccentIndex(context: Context, v: Int) { context.dataStore.edit { it[ACCENT] = v } }
    suspend fun setGestureMode(context: Context, v: Int) { context.dataStore.edit { it[GESTURE] = v } }
}
