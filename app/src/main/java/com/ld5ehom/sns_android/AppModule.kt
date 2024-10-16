package com.ld5ehom.sns_android

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
// AppModule provides application-level dependencies
// (AppModule은 애플리케이션 수준의 의존성을 제공)
abstract class AppModule {

    @Binds
    // Binds the Application instance to the Context interface for dependency injection
    // (Application 인스턴스를 Context 인터페이스에 바인딩하여 의존성 주입에 사용)
    abstract fun bindContext(application: Application): Context
}
