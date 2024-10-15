package com.ld5ehom.domain.usecase.login

interface GetTokenUseCase {
    suspend operator fun invoke(): String?
}