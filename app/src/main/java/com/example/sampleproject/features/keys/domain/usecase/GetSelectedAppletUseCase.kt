package com.example.sampleproject.features.keys.domain.usecase

import com.example.sampleproject.features.keys.domain.model.KnownApplet
import com.example.sampleproject.features.keys.domain.repository.AppletRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedAppletUseCase @Inject constructor(
    private val repository: AppletRepository
) {
    operator fun invoke(): Flow<KnownApplet> = repository.selectedApplet
}
