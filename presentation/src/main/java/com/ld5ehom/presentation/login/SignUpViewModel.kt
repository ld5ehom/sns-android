package com.ld5ehom.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import com.ld5ehom.domain.usecase.login.SignUpUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
// ViewModel for handling sign-up logic with Orbit MVI and Hilt for dependency injection
// (회원가입 로직을 처리하는 ViewModel, Orbit MVI와 Hilt로 의존성 주입 처리)
class SignUpViewModel @Inject constructor(
    // Injected domain/usecase/SignUpUseCase to handle the sign-up process (회원가입 과정을 처리하기 위한 SignUpUseCase 주입)
    private val signUpUseCase: SignUpUseCase
) : ViewModel(),
    ContainerHost<SignUpState, SignUpSideEffect> {

    // Container for managing state and side effects in Orbit MVI
    // (Orbit MVI에서 상태와 부수 효과를 관리하기 위한 컨테이너)
    override val container: Container<SignUpState, SignUpSideEffect> = container(
        initialState = SignUpState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                // Handles any exceptions by posting a toast side effect with the error message
                // (예외가 발생하면 에러 메시지를 포함한 토스트 부수 효과를 게시)
                intent {
                    postSideEffect(SignUpSideEffect.Toast(throwable.message.orEmpty()))
                }
            }
        }
    )

    // Updates the state with the entered ID
    fun onIdChange(id: String) = intent {
        reduce {
            state.copy(id = id)
        }
    }

    // Updates the state with the entered username
    fun onUsernameChange(username: String) = intent {
        reduce {
            state.copy(username = username)
        }
    }

    // Updates the state with the entered password
    fun onPasswordChange(pw: String) = intent {
        reduce {
            state.copy(password = pw)
        }
    }

    // Updates the state with the re-entered password for confirmation
    fun onRepeatPasswordChange(pw: String) = intent {
        reduce {
            state.copy(repeatPassword = pw)
        }
    }

    // Handles the sign-up button click event
    fun onSignUpClick() = intent {
        // Checks if the password and repeat password match, if not, shows a toast message
        // (비밀번호와 확인 비밀번호가 일치하지 않으면 토스트 메시지 표시)
        if(state.password != state.repeatPassword){
            postSideEffect(SignUpSideEffect.Toast(message = "Please check your password again."))
            return@intent
        }

        // Executes the sign-up use case and checks if the sign-up was successful
        // (회원가입 유즈케이스를 실행하고 성공 여부를 확인)
        val isSuccessful = signUpUseCase(
            id = state.id,
            username = state.username,
            password = state.password
        ).getOrThrow()

        // If successful, navigates to the login screen and shows a success message
        // (성공하면 로그인 화면으로 이동하고 성공 메시지 표시)
        if(isSuccessful){
            postSideEffect(SignUpSideEffect.NavigateToLoginScreen)
            postSideEffect(SignUpSideEffect.Toast("Sign-up successful"))
        }
    }
}

// Immutable state class to hold the sign-up form data
// (회원가입 폼 데이터를 저장하는 불변 상태 클래스)
@Immutable
data class SignUpState(
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

// Interface for handling side effects in the sign-up process
// (회원가입 과정에서 부수 효과를 처리하는 인터페이스)
sealed interface SignUpSideEffect {
    class Toast(val message: String) : SignUpSideEffect  // Toast message side effect
    object NavigateToLoginScreen : SignUpSideEffect  // Navigation to the login screen
}
