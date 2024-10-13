package com.ld5ehom.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.ld5ehom.domain.usecase.login.LoginUseCase
import javax.inject.Inject

@HiltViewModel
// ViewModel class that handles login logic with Hilt for dependency injection
// Hilt로 의존성 주입을 받아 로그인 로직을 처리하는 ViewModel 클래스
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    // Function called when the login button is clicked  (로그인 버튼이 클릭되었을 때 호출되는 함수)
    fun onLoginClick(){
        val id = ""
        val password = ""

        // Launch a coroutine within the ViewModel's scope to handle login asynchronously
        // ViewModel의 범위 내에서 코루틴을 실행하여 비동기적으로 로그인 처리
        viewModelScope.launch {
            loginUseCase(id, password)
        }
    }
}