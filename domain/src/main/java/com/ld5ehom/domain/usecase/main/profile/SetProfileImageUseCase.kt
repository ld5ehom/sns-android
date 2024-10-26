package com.ld5ehom.domain.usecase.main.profile

// Interface for setting the user's profile image
// (사용자의 프로필 이미지를 설정하기 위한 인터페이스)
interface SetProfileImageUseCase {
    // Updates the profile image with the provided content URI (as a String)
    // Note: Android's Uri class cannot be used directly in the domain layer, so the content URI is passed as a String.
    // (참고: Android의 Uri 클래스는 도메인 계층에서 사용할 수 없으므로 content URI를 String으로 받음)
    suspend operator fun invoke(contentUri:String):Result<Unit>
}
