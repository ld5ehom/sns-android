package com.ld5ehom.domain.usecase.login

interface SetTokenUseCase {
    suspend operator fun invoke(token: String)
}