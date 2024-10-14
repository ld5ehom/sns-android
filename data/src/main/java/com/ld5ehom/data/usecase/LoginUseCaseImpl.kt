package com.ld5ehom.data.usecase

import com.ld5ehom.data.model.LoginParam
import com.ld5ehom.data.retrofit.UserService
import com.ld5ehom.domain.usecase.login.LoginUseCase
import javax.inject.Inject

// This constructor is annotated with @Inject to enable dependency injection using Dagger
// (Dagger를 통해 의존성을 주입받기 위해 @Inject로 표시된 생성자)
class LoginUseCaseImpl @Inject constructor(
    private val userService: UserService,  // Injected UserService to handle login API calls (로그인 API 호출을 처리하는 UserService를 주입받음)
) : LoginUseCase {
    // Function that performs login by calling the API with user credentials
    // (사용자 자격 증명을 전달해 로그인 API를 호출하는 함수)
    override suspend fun invoke(id: String, password: String): Result<String> = kotlin.runCatching {
        // Convert id and password into a request body for the login API
        // (id와 password를 로그인 API 요청 본문으로 변환)
        val requestBody = LoginParam(loginId = id, password = password).toRequestBody()
        // Call the login API and retrieve the response data
        // (로그인 API 호출 후 응답 데이터 획득)
        userService.login(requestBody = requestBody).data
    }
}

