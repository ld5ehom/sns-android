package com.ld5ehom.domain.usecase.login

// Interface for sign-up use case which returns a token based on the provided ID, username and password
// 제공된 ID, username 그리고 비밀번호를 바탕으로 토큰을 반환하는 회원가입 유스케이스 인터페이스
interface SignUpUseCase {

    // Takes ID, username and password as inputs and returns a result containing the token
    // ID, username 그리고 비밀번호를 입력받아 토큰을 결과로 반환
    suspend operator fun invoke(
        id:String,
        username:String,
        password:String
    ):Result<Unit>
}
