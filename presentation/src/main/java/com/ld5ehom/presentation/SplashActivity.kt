package com.ld5ehom.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.ld5ehom.domain.usecase.login.GetTokenUseCase
import com.ld5ehom.presentation.login.LoginActivity
import com.ld5ehom.presentation.main.MainActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    // Injects GetTokenUseCase to handle token retrieval
    // (토큰 검색을 처리하기 위한 GetTokenUseCase 주입)
    @Inject
    lateinit var getTokenUseCase: GetTokenUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launches a coroutine in the lifecycle scope to check the login status (로그인 상태를 확인하기 위한 코루틴 실행)
        lifecycleScope.launch {
            val isLoggedIn = !getTokenUseCase().isNullOrEmpty()  // Checks if the token exists

            if (isLoggedIn) {
                // If the user is logged in, navigate to the MainActivity (로그인된 경우 MainActivity로 이동)
                startActivity(
                    Intent(
                        this@SplashActivity, MainActivity::class.java
                    ).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK  // Clear the task stack and start MainActivity (백스택을 지우고 MainActivity 시작)
                    }
                )
            } else {
                // If the user is not logged in, navigate to the LoginActivity (로그인되지 않은 경우 LoginActivity로 이동)
                startActivity(
                    Intent(
                        this@SplashActivity, LoginActivity::class.java
                    ).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK  // Clear the task stack and start LoginActivity (백스택을 지우고 LoginActivity 시작)
                    }
                )
            }
        }
    }
}
