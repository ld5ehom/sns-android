package com.ld5ehom.data.usecase

import com.ld5ehom.data.UserDataStore
import com.ld5ehom.domain.usecase.login.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
) :ClearTokenUseCase{
    override suspend fun invoke() :Result<Unit> = kotlin.runCatching{
        userDataStore.clear()
    }
}