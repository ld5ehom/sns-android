package com.ld5ehom.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import com.ld5ehom.domain.usecase.login.LoginUseCase
import com.ld5ehom.domain.usecase.login.SetTokenUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
// ViewModel class that handles login logic using Orbit MVI for state management and side effects
// Orbit MVI를 사용해 상태 관리 및 부수 효과를 처리하는 ViewModel 클래스
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,  // LoginUseCase is injected to handle login logic (로그인 로직을 처리하기 위해 LoginUseCase가 주입됨)
    private val setTokenUseCase:SetTokenUseCase,
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {

    // Orbit container that manages the LoginState and handles side effects
    // Orbit container가 로그인 상태를 관리하고 부수 효과를 처리
    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                // Handle any exceptions by posting a side effect with an error message (예외가 발생하면 에러 메시지와 함께 부수 효과를 게시함)
                intent {
                    postSideEffect(LoginSideEffect.Toast(message = throwable.message.orEmpty()))
                }
            }
        }
    )

    // Function called when login button is clicked, executing the login use case
    // 로그인 버튼 클릭 시 호출되는 함수로, 로그인 유즈케이스를 실행
    fun onLoginClick() = intent {
        val id = state.id
        val password = state.password
        val token = loginUseCase(id, password).getOrThrow()  // Perform login and get the token
        // postSideEffect(LoginSideEffect.Toast(message = "token = $token"))  // Show token in a toast message
        setTokenUseCase(token)  // save token
        postSideEffect(LoginSideEffect.NavigateToMainActivity)
    }

    // Update the state when the id input changes (id 입력이 변경될 때 상태 업데이트)
    fun onIdChange(id: String) = blockingIntent {
        reduce {
            state.copy(id = id)  // Create a new state with the updated id
        }
    }

    // Update the state when the password input changes (비밀번호 입력이 변경될 때 상태 업데이트)
    fun onPasswordChange(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)  // Create a new state with the updated password
        }
    }
}

// Immutable state class to hold the login state (id, password)
// 로그인 상태(id, 비밀번호)를 저장하는 불변 상태 클래스
@Immutable
data class LoginState(
    val id: String = "",
    val password: String = ""
)

// Interface for handling side effects, such as showing toast messages
// 부수 효과를 처리하는 인터페이스, Such as: 토스트 메시지 표시
sealed interface LoginSideEffect {
    class Toast(val message: String) : LoginSideEffect
    object NavigateToMainActivity:LoginSideEffect // Navigate to MainActivity
}
