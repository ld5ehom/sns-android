package com.ld5ehom.data.usecase.main.profile

import com.ld5ehom.data.di.ld5ehom_HOST
import com.ld5ehom.domain.model.Image
import com.ld5ehom.domain.usecase.file.GetImageUseCase
import com.ld5ehom.domain.usecase.file.UploadImageUseCase
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,  // Handles image upload to the server (이미지 서버 업로드 처리)
    private val getImageUseCase: GetImageUseCase,  // Retrieves image information from the content URI (콘텐츠 URI에서 이미지 정보 가져옴)
    private val setMyUserUseCase: SetMyUserUseCase,  // Updates user profile information (사용자 프로필 정보 업데이트)
    private val getMyUserUseCase: GetMyUserUseCase,  // Retrieves the current user information (현재 사용자 정보 가져옴)
) : SetProfileImageUseCase {

    // Executes the use case to update the user's profile image (사용자의 프로필 이미지를 업데이트하는 유즈케이스 실행)
    override suspend fun invoke(contentUri: String): Result<Unit> = kotlin.runCatching {
        // Step 1: Fetch the current user information (현재 사용자 정보 가져오기)
        val user = getMyUserUseCase().getOrThrow()

        // Step 2: Retrieve the image information from the provided content URI (제공된 콘텐츠 URI에서 이미지 정보 가져오기)
        val image: Image = getImageUseCase(contentUri = contentUri)
            ?: throw NullPointerException("Image not found")

        // Step 3: Upload the image to the server and get the image path (이미지를 서버에 업로드하고 이미지 경로 가져오기)
        val imagePath = uploadImageUseCase(
            image
        ).getOrThrow()

        // Step 4: Update the user's profile with the new image URL (사용자의 프로필을 새 이미지 URL로 업데이트)
        setMyUserUseCase(
            profileImageUrl = "$ld5ehom_HOST/$imagePath"  // Host
        ).getOrThrow()
    }
}
