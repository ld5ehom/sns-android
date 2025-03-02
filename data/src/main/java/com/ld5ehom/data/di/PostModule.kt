package com.ld5ehom.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.ld5ehom.data.usecase.main.post.GetImageListUseCaseImpl
import com.ld5ehom.domain.usecase.main.post.GetImageListUseCase

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PostModule {

    @Binds
    abstract fun bindGetImageListUseCase(uc: GetImageListUseCaseImpl): GetImageListUseCase
}