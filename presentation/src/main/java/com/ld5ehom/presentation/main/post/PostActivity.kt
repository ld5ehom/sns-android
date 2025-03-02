package com.ld5ehom.presentation.main.post

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ld5ehom.presentation.theme.SNSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    // PostActivity class that sets up the posting feature
    // 게시물을 작성 및 관리하기 위한 PostActivity 클래스
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the Compose UI content with the SNSTheme and PostNavHost
        // SNSTheme와 PostNavHost를 사용해 Compose UI 콘텐츠 설정
        setContent {
            SNSTheme {
                // Initialize and manage navigation between post-related screens
                // 게시물 관련 화면 간의 네비게이션을 초기화하고 관리
                PostNavHost()
            }
        }
    }
}
