package com.voidlauncher.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "glass_launcher_prefs")
private val SELECTED_PACK = stringPreferencesKey("selected_icon_pack")

object Prefs {
    fun selectedPack(context: Context): Flow<String?> =
        context.dataStore.data.map { it[SELECTED_PACK] }

    suspend fun setSelectedPack(context: Context, value: String?) {
        context.dataStore.edit { prefs ->
            if (value == null) prefs.remove(SELECTED_PACK) else prefs[SELECTED_PACK] = value
        }
    }
}
