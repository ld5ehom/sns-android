package com.ld5ehom.domain.usecase.main.profile

interface SetMyUserUseCase {
    suspend operator fun invoke(
        username: String? = null,
        profileImageUrl:String? = null
    ): Result<Unit>
}