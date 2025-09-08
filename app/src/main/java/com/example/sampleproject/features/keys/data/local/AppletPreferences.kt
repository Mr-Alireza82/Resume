package com.example.sampleproject.features.keys.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sampleproject.features.keys.domain.model.KnownApplet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.appletDataStore by preferencesDataStore(name = "applet_prefs")

class AppletPreferences(private val context: Context) {

    private val selectedAppletKey = stringPreferencesKey("selected_applet")

    val selectedAppletFlow: Flow<KnownApplet> = context.appletDataStore.data.map { prefs ->
        val stored = prefs[selectedAppletKey]
        stored?.let { runCatching { KnownApplet.valueOf(it) }.getOrNull() } ?: KnownApplet.MyApplet
    }

    suspend fun updateSelectedApplet(applet: KnownApplet) {
        context.appletDataStore.edit { prefs ->
            prefs[selectedAppletKey] = applet.name
        }
    }
}
