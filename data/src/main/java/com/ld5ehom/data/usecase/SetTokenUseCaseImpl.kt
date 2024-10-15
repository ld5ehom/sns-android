package com.ld5ehom.data.usecase

import com.ld5ehom.data.UserDataStore
import com.ld5ehom.domain.usecase.login.SetTokenUseCase
import javax.inject.Inject

class SetTokenUseCaseImpl @Inject constructor(
    // Injected UserDataStore to handle token storage
    // (토큰 저장을 관리하기 위한 UserDataStore 주입)
    private val userDataStore: UserDataStore
) : SetTokenUseCase {
    // Saves the provided token using UserDataStore
    override suspend fun invoke(token: String) {
        userDataStore.setToken(token)  // Calls setToken to store the token in the DataStore
    }
}
