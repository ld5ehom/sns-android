package com.ld5ehom.sns_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import com.ld5ehom.sns_android.ui.theme.SNSTheme
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class LoginActivity : ComponentActivity() {

    // Lazily initializes and retrieves the app's dependency container from the Application context.
    // 애플리케이션 컨텍스트에서 앱의 의존성 컨테이너를 지연 초기화하여 검색함.
    private val container by lazy {
        (this.application as App).appContainer
    }

    // Initializes the LoginViewModel using a factory from the dependency container.
    // 의존성 컨테이너에서 팩토리를 사용하여 LoginViewModel을 초기화함.
    private val viewModel: LoginViewModel by viewModels {
        container.createLoginViewModelFactory()
    }


    // Called when the activity is first created
    // 엑티비티가 처음 생성될 때 호출됨
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Observes UI state and reacts to user state changes
        // UI 상태를 관찰하고 사용자 상태 변경에 반응함
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it.userState) {
                        UserState.NONE -> {
                            // nothing to do
                        }

                        UserState.FAILED -> {
                            // Show login failed message
                            Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다", Toast.LENGTH_SHORT)
                                .show()
                        }

                        UserState.LOGGED_IN -> {
                            // Start the UserInfoActivity and finish LoginActivity
                            // UserInfoActivity 시작하고 LoginActivity 종료
                            startActivity(Intent(this@LoginActivity, UserInfoActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }

        // Compose UI and bind ViewModel to the login screen
        // Compose UI를 사용하여 ViewModel과 로그인 화면을 바인딩함
        setContent {
            SNSTheme {
                val uiState = viewModel.uiState.collectAsState().value
                LoginScreen(
                    id = uiState.id,
                    pw = uiState.pw,
                    onIdChange = viewModel::onIdChange,
                    onPwChange = viewModel::onPwChange,
                    onLoginClick = viewModel::login
                )
            }
        }
    }
}

