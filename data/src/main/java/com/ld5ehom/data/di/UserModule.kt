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
import com.ld5ehom.domain.usecase.login.ClearTokenUseCase
import com.ld5ehom.domain.usecase.login.GetTokenUseCase
import com.ld5ehom.domain.usecase.login.LoginUseCase
import com.ld5ehom.domain.usecase.login.SignUpUseCase
import com.ld5ehom.domain.usecase.login.SetTokenUseCase
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetMyUserUseCase


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

    @Binds
    // Binds GetTokenUseCaseImpl implementation to the GetTokenUseCase interface
    // (GetTokenUseCase 인터페이스에 GetTokenUseCaseImpl 구현체를 바인딩)
    abstract fun bindGetTokenUseCase(uc: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    // Binds SetTokenUseCaseImpl implementation to the SetTokenUseCase interface
    // (SetTokenUseCase 인터페이스에 SetTokenUseCaseImpl 구현체를 바인딩)
    abstract fun bindSetTokenUseCase(uc: SetTokenUseCaseImpl): SetTokenUseCase

    @Binds
    // Binds ClearTokenUseCaseImpl implementation to the ClearTokenUseCase interface
    // (ClearTokenUseCase 인터페이스에 ClearTokenUseCaseImpl 구현체를 바인딩)
    abstract fun bindClearTokenUseCase(uc: ClearTokenUseCaseImpl): ClearTokenUseCase

    @Binds
    // Binds the GetMyUserUseCaseImpl to GetMyUserUseCase in the Dagger dependency graph.
    // (Dagger 의존성 그래프에서 GetMyUserUseCaseImpl을 GetMyUserUseCase에 바인딩함)
    abstract fun bindGetMyUserUseCase(uc: GetMyUserUseCaseImpl):GetMyUserUseCase

    @Binds
    // Binds the SetMyUserUseCaseImpl implementation to the SetMyUserUseCase interface
    // (SetMyUserUseCase 인터페이스에 SetMyUserUseCaseImpl 구현체를 바인딩함)
    abstract fun bindUpdateMyNameUseCase(uc: SetMyUserUseCaseImpl): SetMyUserUseCase

}
