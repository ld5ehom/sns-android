package com.ld5ehom.data.usecase.main.profile

import com.ld5ehom.data.model.UpdateMyInfoParam
import com.ld5ehom.data.retrofit.UserService
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetMyUserUseCase
import javax.inject.Inject

// Implements SetMyUserUseCase to handle user profile updates
// (SetMyUserUseCase를 구현하여 사용자 프로필 업데이트 처리)
class SetMyUserUseCaseImpl @Inject constructor(
    // Injected UserService for API interactions and GetMyUserUseCase to fetch current user data
    // (API 상호작용을 위한 UserService와 현재 사용자 데이터를 가져오는 GetMyUserUseCase 주입)
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
) : SetMyUserUseCase {

    // Updates the user profile with the provided username and profile image URL
    // (제공된 사용자 이름과 프로필 이미지 URL로 사용자 프로필을 업데이트)
    override suspend fun invoke(
        username: String?, // New username
        profileImageUrl: String? // New profile image URL
    ): Result<Unit> = kotlin.runCatching {
        val user = getMyUserUseCase().getOrThrow()  // Fetches the current user data

        // Creates request body with the updated user data
        val requestBody = UpdateMyInfoParam(
            userName = username ?: user.username, // Uses existing username if new one is null (새 이름이 null일 경우 기존 이름 사용)
            profileFilePath = user.profileImageUrl.orEmpty(), // Uses existing image URL if new one is null (새 이미지가 null일 경우 기존 이미지 사용)
            extraUserInfo = "" // Additional info placeholder
        ).toRequestBody()

        userService.patchMyPage(requestBody) // Calls the API to update the user's profile
    }
}
