package com.ld5ehom.sns_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.ld5ehom.presentation.theme.SNSTheme

/**
 * @author Taewook.ok
 */
// Activity to display and manage user information
// 사용자 정보를 표시하고 관리하는 액티비티
class UserInfoActivity : ComponentActivity() {

    // Lazily initializes and retrieves the UserLocalDataSource from the application's dependency container.
    // 애플리케이션의 의존성 컨테이너에서 사용자 로컬 데이터 소스를 지연 초기화하여 검색함.
    private val userLocalDataSource by lazy {
        (application as App).appContainer.createUserLocalDataSource()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets the UI content for this activity
        // 이 액티비티의 UI 내용을 설정
        setContent {
            // Applies the SNSTheme defined in the app
            // 앱에서 정의된 SNSTheme 적용
            SNSTheme {
                // Creates a surface container that fills the max size
                // 최대 크기를 채우는 표면 컨테이너 생성
                Surface {
                    // Aligns content to the center of the box
                    // 박스의 중심에 내용을 정렬
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Vertical column for user interface elements
                        // 사용자 인터페이스 요소를 위한 수직 열
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Holds the token state, initially empty
                            // 토큰 상태를 보유, 초기에는 비어 있음
                            var token by remember { mutableStateOf("") }
                            // Effect to fetch token from local data source when component is launched
                            // 컴포넌트가 시작될 때 로컬 데이터 소스에서 토큰을 가져오기 위한 이펙트
                            LaunchedEffect(Unit){
                                launch {
                                    token = userLocalDataSource.getToken().orEmpty() // Retrieves token, defaults to empty if null // 토큰을 검색, null이면 비어 있는 것으로 기본 설정
                                }
                            }
                            // Displays the token 토큰 표시
                            Text(text = "$token")
                            // Adds vertical space  수직 공간 추가
                            Spacer(modifier = Modifier.height(20.dp))
                            // Button to log out and clear local data 로그아웃하고 로컬 데이터를 지우는 버튼
                            TextButton(onClick = {
                                lifecycleScope.launch {
                                    userLocalDataSource.clear() // Clears local data
                                    startActivity(Intent(this@UserInfoActivity, LoginActivity::class.java)) // Navigates to LoginActivity // LoginActivity로 이동
                                    finish() // Finishes this activity
                                }
                            }) {
                                Text(text = "Logout")
                            }
                        }
                    }
                }
            }
        }
    }
}
