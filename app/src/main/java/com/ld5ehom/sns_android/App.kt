package com.ld5ehom.sns_android

import android.app.Application

// Main Application class responsible for initializing and providing the app's dependency container
// 애플리케이션의 메인 클래스로, 앱의 의존성 컨테이너를 초기화하고 제공하는 역할을 담당
class App : Application() {
    // Initializes and holds the application's dependency container to provide a common access path for Android components.
    // 의존성 컨테이너를 초기화하고 보유하여, 안드로이드 컴포넌트들이 쉽게 접근할 수 있는 일반적인 경로를 제공함.
    val appContainer: AppContainer = AppContainer(context = this)
}
