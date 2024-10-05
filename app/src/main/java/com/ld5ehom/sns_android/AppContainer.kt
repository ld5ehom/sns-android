package com.ld5ehom.sns_android

import androidx.activity.viewModels
import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// Container for managing dependency injections in the application
// 애플리케이션에서 의존성 주입을 관리하는 컨테이너
class AppContainer constructor(private val context: Context) {

    // Creates and configures a Retrofit instance
    // Retrofit 인스턴스 생성 및 구성
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/api/") // Sets the base URL for the API
            .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType())) // Adds a JSON converter factory // JSON 변환기 팩토리 추가
            .build()
    }

    // Creates a LoginRetrofitService using the configured Retrofit instance
    // 구성된 Retrofit 인스턴스를 사용하여 LoginRetrofitService 생성
    fun createLoginRetrofitService(): LoginRetrofitService {
        return createRetrofit().create(LoginRetrofitService::class.java)
    }

    // Creates a UserLocalDataSource with the application context
    // 애플리케이션 컨텍스트를 사용하여 UserLocalDataSource 생성
    fun createUserLocalDataSource(): UserLocalDataSource {
        return UserLocalDataSource(context)
    }

    // Creates a UserRemoteDataSource using a LoginRetrofitService
    // LoginRetrofitService를 사용하여 UserRemoteDataSource 생성
    fun createUserRemoteDataSource(): UserRemoteDataSource {
        return UserRemoteDataSource(createLoginRetrofitService())
    }

    // Creates a UserDataRepository by combining local and remote data sources
    // 로컬 및 원격 데이터 소스를 결합하여 UserDataRepository 생성
    fun createUserDataRepository(): UserDataRepository {
        return UserDataRepository(
            localDataSource = createUserLocalDataSource(),
            remoteDataSource = createUserRemoteDataSource()
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
