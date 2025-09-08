package com.example.sampleproject.features.keys.domain.usecase

import com.example.sampleproject.features.keys.domain.model.KnownApplet
import com.example.sampleproject.features.keys.domain.repository.AppletRepository
import javax.inject.Inject


class UpdateSelectedAppletUseCase @Inject constructor(
    private val repository: AppletRepository
) {
    suspend operator fun invoke(applet: KnownApplet) = repository.updateSelectedApplet(applet)
}
