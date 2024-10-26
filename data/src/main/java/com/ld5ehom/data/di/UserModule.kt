package com.ld5ehom.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.data.usecase.ClearTokenUseCaseImpl
import com.ld5ehom.data.usecase.GetTokenUseCaseImpl
import com.ld5ehom.data.usecase.LoginUseCaseImpl
import com.ld5ehom.data.usecase.SetTokenUseCaseImpl
import com.ld5ehom.data.usecase.SignUpUseCaseImpl
import com.ld5ehom.data.usecase.main.profile.GetMyUserUseCaseImpl
import com.ld5ehom.data.usecase.main.profile.SetMyUserUseCaseImpl
import com.ld5ehom.data.usecase.main.profile.SetProfileImageUseCaseImpl
import com.ld5ehom.domain.usecase.login.ClearTokenUseCase
import com.ld5ehom.domain.usecase.login.GetTokenUseCase
import com.ld5ehom.domain.usecase.login.LoginUseCase
import com.ld5ehom.domain.usecase.login.SignUpUseCase
import com.ld5ehom.domain.usecase.login.SetTokenUseCase
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetProfileImageUseCase


@Module
@InstallIn(SingletonComponent::class)
// This module will be installed in the SingletonComponent, making its bindings available application-wide
// (이 모듈은 SingletonComponent에 설치되어 앱 전역에서 바인딩된 의존성을 사용할 수 있음)
abstract class UserModule {

    @Binds
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindSignUpUseCase(uc: SignUpUseCaseImpl):SignUpUseCase

    @Binds
    abstract fun bindGetTokenUseCase(uc: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    abstract fun bindSetTokenUseCase(uc: SetTokenUseCaseImpl): SetTokenUseCase

    @Binds
    abstract fun bindClearTokenUseCase(uc: ClearTokenUseCaseImpl): ClearTokenUseCase

    @Binds
    abstract fun bindGetMyUserUseCase(uc: GetMyUserUseCaseImpl):GetMyUserUseCase

    @Binds
    abstract fun bindUpdateMyNameUseCase(uc: SetMyUserUseCaseImpl): SetMyUserUseCase

    @Binds
    abstract fun bindSetProfileImageUseCase(uc: SetProfileImageUseCaseImpl):SetProfileImageUseCase
}
