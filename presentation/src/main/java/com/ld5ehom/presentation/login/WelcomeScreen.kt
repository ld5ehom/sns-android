package com.ld5ehom.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ld5ehom.presentation.component.CustomButton
import com.ld5ehom.presentation.theme.SNSTheme

@Composable
fun WelcomeScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    // Creates a Surface that fills the screen  화면을 채우는 Surface 생성
    Surface {
        // Creates a Box that fills the screen and aligns its content to the top center
        // 화면을 채우고 내용이 상단 중앙에 정렬되는 Box 생성
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Creates a Column for vertically aligning text elements with top padding
            // 상단 여백이 있는 세로 정렬 Column 생성
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Displays the main title text
                Text(
                    modifier = Modifier.padding(top = 48.dp),
                    text = "SNS",
                    style = MaterialTheme.typography.displayLarge,
                )
                // Displays the subtitle text
                Text(
                    text = "Taewook Park SNS Project",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            // Creates a button that navigates to the login screen when clicked
            // 클릭 시 로그인 화면으로 이동하는 버튼 생성
            CustomButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .align(alignment = Alignment.BottomCenter),
                text = "Login",
                onClick = onNavigateToLoginScreen
            )
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    SNSTheme {
        WelcomeScreen(onNavigateToLoginScreen = {})
    }
}
