package com.ld5ehom.presentation.main.profile

import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import com.ld5ehom.domain.model.User
import com.ld5ehom.domain.usecase.login.ClearTokenUseCase
import com.ld5ehom.domain.usecase.main.profile.GetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetMyUserUseCase
import com.ld5ehom.domain.usecase.main.profile.SetProfileImageUseCase
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
    private val getMyUserUseCase: GetMyUserUseCase,

    // Injected SetMyUserUseCase to update the current user's profile information
    // (현재 사용자의 프로필 정보를 업데이트하기 위한 SetMyUserUseCase 주입)
    private val setMyUserUseCase: SetMyUserUseCase,

    // Injected SetProfileImageUseCase to handle the profile image update
    // (프로필 이미지 업데이트를 처리하기 위한 SetProfileImageUseCase 주입)
    private val setProfileImageUseCase: SetProfileImageUseCase
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

    // Handles the username change by calling setMyUserUseCase to update the user's profile
    // (사용자의 프로필을 업데이트하기 위해 setMyUserUseCase를 호출하여 사용자 이름을 변경 처리함)
    fun onUsernameChange(username: String) = intent {
        setMyUserUseCase(
            username = username
        ).getOrThrow()   // Executes the use case to update the username and throws an exception if it fails
        load()
    }

    // Handles the profile image change by calling setProfileImageUseCase to update the user's profile image
    // (사용자의 프로필 이미지를 업데이트하기 위해 setProfileImageUseCase를 호출하여 이미지 변경 처리함)
    fun onImageChange(contentUri: Uri?) = intent {
        setProfileImageUseCase(
            contentUri = contentUri.toString()
        ).getOrThrow()  // Executes the use case to update the profile image and throws an exception if it fails
        load()
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
