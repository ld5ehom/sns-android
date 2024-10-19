package com.ld5ehom.data.usecase.main.profile

import com.ld5ehom.data.model.toDomainModel
import com.ld5ehom.data.retrofit.UserService
import com.ld5ehom.domain.model.User
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import javax.inject.Inject

// GetMyUserUseCaseImpl retrieves user data by calling the UserService API and mapping it to the domain model
// (GetMyUserUseCaseImpl는 UserService API를 호출하여 사용자 데이터를 가져오고 이를 도메인 모델로 매핑함)
class GetMyUserUseCaseImpl @Inject constructor(
    // Injected UserService to handle network API calls for user data
    // (사용자 데이터를 가져오기 위한 네트워크 API 호출을 처리하기 위해 UserService 주입)
    private val userService: UserService
) : GetMyUserUseCase {
    // Implements the invoke method to fetch user data from the API and return it as a Result
    // (API에서 사용자 데이터를 가져오고 이를 Result로 반환하는 invoke 메서드 구현)
    override suspend fun invoke(): Result<User> = kotlin.runCatching {
        // Calls the myPage() API, gets the response, and converts the DTO to the User domain model
        // (myPage() API를 호출하고 응답 데이터를 가져온 후 DTO를 User 도메인 모델로 변환)
        userService.myPage().data.toDomainModel()
    }
}
