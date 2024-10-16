package com.ld5ehom.presentation.main.profile

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import com.ld5ehom.domain.model.User
import com.ld5ehom.domain.usecase.login.ClearTokenUseCase
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

// ViewModel that manages the state and side effects for the profile screen
// (프로필 화면의 상태와 부수 효과를 관리하는 ViewModel)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    // Injected ClearTokenUseCase to clear the user's token during logout
    // (로그아웃 시 사용자의 토큰을 삭제하기 위한 ClearTokenUseCase 주입)
    private val clearTokenUseCase: ClearTokenUseCase,

    // Injected GetMyUserUseCase to retrieve the current user's profile information
    // (현재 사용자의 프로필 정보를 가져오기 위한 GetMyUserUseCase 주입)
    private val getMyUserUseCase: GetMyUserUseCase
) : ViewModel(), ContainerHost<ProfileState, ProfileSideEffect> {

    // Container for managing state and side effects in Orbit MVI
    // (Orbit MVI에서 상태와 부수 효과를 관리하기 위한 컨테이너)
    override val container: Container<ProfileState, ProfileSideEffect> =
        container(
            initialState = ProfileState(),
            buildSettings = {
                // Handles any exceptions by posting a toast side effect with the error message
                // (예외가 발생하면 에러 메시지를 포함한 토스트 부수 효과를 게시)
                this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    intent {
                        postSideEffect(ProfileSideEffect.Toast(throwable.message.orEmpty()))
                    }
                }
            }
        )

    // Initializes the ViewModel and loads the user profile
    // (ViewModel을 초기화하고 사용자 프로필을 로드)
    init {
        load()
    }

    // Loads the user's profile by calling the GetMyUserUseCase
    // (GetMyUserUseCase를 호출하여 사용자 프로필을 로드)
    private fun load() = intent {
        val user: User = getMyUserUseCase().getOrThrow()  // Retrieves the user profile or throws an error if it fails
        reduce {
            // Updates the state with the user's profile image and username
            // (사용자의 프로필 이미지와 사용자 이름으로 상태를 업데이트)
            state.copy(
                profileImageUrl = user.profileImageUrl,
                username = user.username
            )
        }
    }

    // Handles the logout action by clearing the token and navigating to the login screen
    // (로그아웃 작업을 처리하고 토큰을 삭제한 후 로그인 화면으로 이동)
    fun onLogoutClick() = intent {
        clearTokenUseCase().getOrThrow()  // Clears the user token
        postSideEffect(ProfileSideEffect.NavigateToLoginActivity)  // Triggers navigation to the login screen
    }
}

// Immutable state class to hold profile data
// (프로필 데이터를 저장하는 불변 상태 클래스)
@Immutable
data class ProfileState(
    val profileImageUrl: String? = null,  // User's profile image URL
    val username: String = ""  // User's username
)

// Interface for handling side effects on the profile screen
// (프로필 화면에서 부수 효과를 처리하는 인터페이스)
sealed interface ProfileSideEffect {
    // Displays a toast message with the provided text
    class Toast(val message: String) : ProfileSideEffect

    // Navigates to the login activity
    object NavigateToLoginActivity : ProfileSideEffect
}
