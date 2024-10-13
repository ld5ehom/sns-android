package com.ld5ehom.presentation.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ld5ehom.presentation.theme.SNSTheme
import dagger.hilt.android.AndroidEntryPoint

// LoginActivity class that sets up the login UI
// 로그인 UI를 설정하는 LoginActivity 클래스
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the UI content with the SNSTheme and LoginNavHost
        // SNSTheme와 LoginNavHost로 UI 콘텐츠 설정
        setContent {
            SNSTheme {
                // Call the LoginNavHost to manage navigation between login-related screens
                // 로그인 관련 화면 간의 네비게이션을 관리하는 LoginNavHost 호출
                LoginNavHost()
            }
        }
    }
}
