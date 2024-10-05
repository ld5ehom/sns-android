package com.ld5ehom.sns_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Taewook.ok
 */
// ViewModel for managing login UI state
// 로그인 UI 상태를 관리하는 ViewModel
class LoginViewModel constructor(
    private val repository: UserDataRepository, // Repository for user data operations
): ViewModel() {

    // Holds the UI state privately
    // UI 상태를 비공개로 보유
    private val _uiState = MutableStateFlow(
        LoginUiState(id = "Taewook", pw = "park") // Default state with preset user credentials
    )
    // Publicly exposes UI state as a read-only flow
    // 읽기 전용 플로우로 UI 상태를 공개적으로 노출
    val uiState = _uiState.asStateFlow()

    init {
        // Checks login state on ViewModel initialization
        // ViewModel 초기화 시 로그인 상태 확인
        viewModelScope.launch {
            if (repository.isLoggedIn()) {
                val isLoggedInBefore = repository.isLoggedIn()
                if (isLoggedInBefore) {
                    // Updates UI state if user is already logged in
                    // 사용자가 이미 로그인되어 있을 경우 UI 상태 업데이트
                    _uiState.update { it.copy(userState = UserState.LOGGED_IN) }
                }
            }
        }
    }

    // Performs login using ID and password from UI state
    // UI 상태에서 ID와 비밀번호를 사용하여 로그인 수행
    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val isLoggedIn = repository.login(
                _uiState.value.id,
                _uiState.value.pw
            )
            val token = repository.getCurrentToken() // Retrieves the current token after login attempt // 로그인 시도 후 현재 토큰 검색
            // Updates UI state based on login success or failure
            // 로그인 성공 여부에 따라 UI 상태 업데이트
            _uiState.update {
                it.copy(userState = if (isLoggedIn) UserState.LOGGED_IN else UserState.FAILED)
            }
        }
    }

    // Updates ID in the UI state
    // UI 상태에서 ID 업데이트
    fun onIdChange(value: String) {
        _uiState.update { it.copy(id = value) }
    }

    // Updates password in the UI state
    // UI 상태에서 비밀번호 업데이트
    fun onPwChange(value: String) {
        _uiState.update { it.copy(pw = value) }
    }

}
