package com.ld5ehom.data.usecase

import com.ld5ehom.data.model.SignUpParam
import com.ld5ehom.data.retrofit.UserService
import com.ld5ehom.domain.usecase.login.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userService: UserService  // Injected UserService to handle API requests for user sign-up (회원가입 API 요청을 처리하기 위한 UserService 주입)
) : SignUpUseCase {
    override suspend fun invoke(id: String, username: String, password: String): Result<Boolean> = kotlin.runCatching {
        // Create a SignUpParam object with user data to be sent in the request body
        // (요청 본문에 보낼 사용자 데이터를 담은 SignUpParam 객체 생성)
        val requestBody = SignUpParam(
            loginId = id,
            name = username,
            password = password,
            extraUserInfo = "",  // Additional user info, currently empty
            profileFilePath = ""  // User profile file path, currently empty
        ).toRequestBody()

        // Send the sign-up request via UserService and check if the result is "SUCCESS"
        // (UserService를 통해 회원가입 요청을 전송하고 결과가 "SUCCESS"인지 확인)
        userService.signUp(requestBody = requestBody).result == "SUCCESS"
    }
}
