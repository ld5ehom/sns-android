package com.ld5ehom.domain.usecase.login

interface ClearTokenUseCase {
    suspend operator fun invoke():Result<Unit>
}