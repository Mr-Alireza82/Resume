package com.example.sampleproject.features.keys.domain.repository

import com.example.sampleproject.features.keys.domain.model.KnownApplet
import kotlinx.coroutines.flow.Flow

interface AppletRepository {
    val selectedApplet: Flow<KnownApplet>
    suspend fun updateSelectedApplet(applet: KnownApplet)
}
