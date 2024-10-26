package com.ld5ehom.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.data.usecase.file.GetImageUseCaseImpl
import com.ld5ehom.data.usecase.file.GetInputStreamUseCaseImpl
import com.ld5ehom.data.usecase.file.UploadImageUseCaseImpl
import com.ld5ehom.domain.usecase.file.GetImageUseCase
import com.ld5ehom.domain.usecase.file.GetInputStreamUseCase
import com.ld5ehom.domain.usecase.file.UploadImageUseCase

@Module
@InstallIn(SingletonComponent::class)
// This module binds implementations of use cases to their respective interfaces within a SingletonComponent.
// 이 모듈은 SingletonComponent 내에서 유즈케이스 구현체들을 해당 인터페이스에 바인딩합니다.
abstract class FileModule {

    @Binds
    abstract fun bindGetInputStreamUseCase(uc: GetInputStreamUseCaseImpl): GetInputStreamUseCase

    @Binds
    abstract fun bindGetImageUseCase(uc: GetImageUseCaseImpl): GetImageUseCase

    @Binds
    abstract fun bindUploadImageUseCase(uc: UploadImageUseCaseImpl): UploadImageUseCase
}
