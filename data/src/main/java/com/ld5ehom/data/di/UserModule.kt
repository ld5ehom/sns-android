package com.ld5ehom.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.data.usecase.LoginUseCaseImpl
import com.ld5ehom.data.usecase.SignUpUseCaseImpl
import com.ld5ehom.domain.usecase.login.LoginUseCase
import com.ld5ehom.domain.usecase.login.SignUpUseCase

@Module
@InstallIn(SingletonComponent::class)
// This module will be installed in the SingletonComponent, making its bindings available application-wide
// (이 모듈은 SingletonComponent에 설치되어 앱 전역에서 바인딩된 의존성을 사용할 수 있음)
abstract class UserModule {

    @Binds
    // Binds LoginUseCaseImpl to the LoginUseCase interface
    // (LoginUseCaseImpl을 LoginUseCase 인터페이스에 바인딩)
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase

    @Binds
    // Binds SignUpUseCaseImpl to the SignUpUseCase interface
    abstract fun bindSignUpUseCase(uc: SignUpUseCaseImpl):SignUpUseCase

}
