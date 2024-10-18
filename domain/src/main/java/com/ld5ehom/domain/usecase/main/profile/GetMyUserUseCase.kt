package com.ld5ehom.domain.usecase.main.profile

import com.ld5ehom.domain.model.User

interface GetMyUserUseCase {
    suspend operator fun invoke():Result<User>
}