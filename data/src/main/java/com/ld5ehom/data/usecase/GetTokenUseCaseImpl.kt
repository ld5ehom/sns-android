package com.ld5ehom.data.usecase

import com.ld5ehom.data.UserDataStore
import com.ld5ehom.domain.usecase.login.GetTokenUseCase
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(
    // Injected UserDataStore to manage user token storage and retrieval
    // (사용자 토큰 저장 및 검색을 관리하는 UserDataStore 주입)
    private val userDataStore: UserDataStore) : GetTokenUseCase {
    override suspend fun invoke(): String? {
        // Retrieves the token from the UserDataStore (UserDataStore에서 토큰을 검색)
        return userDataStore.getToken()
    }
}
