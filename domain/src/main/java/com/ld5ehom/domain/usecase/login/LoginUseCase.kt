package com.ld5ehom.domain.usecase.login

// Interface for login use case which returns a token based on the provided ID and password
// 제공된 ID와 비밀번호를 바탕으로 토큰을 반환하는 로그인 유스케이스 인터페이스
interface LoginUseCase {

    // Takes ID and password as inputs and returns a result containing the token
    // ID와 비밀번호를 입력받아 토큰을 결과로 반환
    suspend operator fun invoke(
        id: String,
        password: String
    ): Result<String>
}
