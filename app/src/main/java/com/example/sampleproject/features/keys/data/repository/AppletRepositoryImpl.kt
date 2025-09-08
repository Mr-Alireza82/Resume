package com.example.sampleproject.features.keys.data.repository

import com.example.sampleproject.features.keys.data.local.AppletPreferences
import com.example.sampleproject.features.keys.domain.model.KnownApplet
import com.example.sampleproject.features.keys.domain.repository.AppletRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AppletRepositoryImpl @Inject constructor(
    private val prefs: AppletPreferences
) : AppletRepository {


    override val selectedApplet: Flow<KnownApplet>
        get() = prefs.selectedAppletFlow


    override suspend fun updateSelectedApplet(applet: KnownApplet) {
        prefs.updateSelectedApplet(applet)
    }
}
