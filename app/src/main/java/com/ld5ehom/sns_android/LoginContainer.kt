package com.ld5ehom.sns_android

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LoginContainer(private val appContainer: AppContainer) {

    // Creates a UserDataRepository by combining local and remote data sources
    // 로컬 및 원격 데이터 소스를 결합하여 UserDataRepository 생성
    fun createUserDataRepository(): UserDataRepository {
        return UserDataRepository(
            localDataSource = appContainer.createUserLocalDataSource(),
            remoteDataSource = appContainer.createUserRemoteDataSource()
        )
    }

    // Creates a ViewModelFactory for creating LoginViewModels, handling state saving
    // LoginViewModel 생성 및 상태 저장을 처리하는 ViewModelFactory 생성
    fun createLoginViewModelFactory(): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory() {
            val repository = createUserDataRepository() // Uses UserDataRepository for ViewModel data management
            override fun <T : ViewModel> create(
                key: String, modelClass: Class<T>, handle: SavedStateHandle
            ): T {
                return LoginViewModel(repository) as T // Instantiates LoginViewModel with dependency injection // 의존성 주입으로 LoginViewModel 인스턴스화
            }
        }
    }

}