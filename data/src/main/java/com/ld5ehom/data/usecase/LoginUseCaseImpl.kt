package com.ld5ehom.data.usecase

import com.ld5ehom.domain.usecase.login.LoginUseCase
import javax.inject.Inject

// This constructor is annotated with @Inject to enable dependency injection using Dagger
// (Dagger를 통해 의존성을 주입받기 위해 @Inject로 표시된 생성자)
class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun invoke(id: String, password: String): Result<String> = kotlin.runCatching {
        "token"
    }
}
