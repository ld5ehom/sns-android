package com.ld5ehom.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ld5ehom.presentation.main.MainActivity
import com.ld5ehom.presentation.component.CustomButton
import com.ld5ehom.presentation.component.CustomTextField
import com.ld5ehom.presentation.theme.SNSTheme
import com.ld5ehom.presentation.theme.*
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
// Login View Model
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUpScreen:() -> Unit,
) {
    // Collects the current state from the ViewModel  (ViewModel에서 현재 상태를 수집)
    val state : LoginState = viewModel.collectAsState().value

    // Gets the current context to display toast messages  (토스트 메시지를 표시하기 위해 현재 Context를 가져옴)
    val context = LocalContext.current

    // Collects side effects (e.g., showing a toast message)
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            LoginSideEffect.NavigateToMainActivity -> {
                context.startActivity(
                    Intent(
                        context, MainActivity::class.java
                    ).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
        }
    }

    // Pass the state and event handlers to the composable function (상태와 이벤트 핸들러를 Composable 함수로 전달)
    LoginScreen(
        id = state.id,
        password = state.password,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onNavigateToSignUpScreen = onNavigateToSignUpScreen,  // Handles navigation to the sign-up screen (회원가입 화면으로 이동 처리)
        onLoginClick = viewModel::onLoginClick  // Handles login action (로그인 버튼 클릭 처리)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
    onLoginClick: () -> Unit,
) {
    // Surface for the login screen UI
    Surface {
        // Outer Column for overall layout and horizontal alignment (전체 레이아웃 및 수평 정렬을 위한 Column)
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header section containing the app title and subtitle (앱 제목과 부제목을 포함한 헤더 섹션)
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Displays the main title text
                Text(
                    text = "SNS",
                    style = MaterialTheme.typography.displaySmall,
                )
                // Displays the subtitle text
                Text(
                    text = "Taewook Park SNS Project",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            // Input section for login form
            Column(
                modifier = Modifier
                    .padding(top = 59.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp),
            ) {
                // Login title
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium
                )

                // ID label and input field
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "ID",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueChange = onIdChange // ID input change handler
                )

                // Password label and input field
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Password",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(), // Password Masking
                    onValueChange = onPasswordChange // Password input change handler (비밀번호 입력 변경 처리기)
                )

                // Login button
                CustomButton(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    text = "Login",
                    onClick = onLoginClick // Log in action handler
                )

                // Spacer to push content to the bottom (내용을 하단으로 밀기 위한 Spacer)
                Spacer(modifier = Modifier.weight(1f))

                // Sign-up navigation link
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 24.dp)
                        .clickable(onClick = onNavigateToSignUpScreen) // Navigate to sign-up screen (회원가입 화면으로 이동)
                ) {
                    Text(text = "Don't have an account?  ")
                    Text(text = "Sign up", color = uclaGold) // Sign-up text with primary color (주 색상으로 회원가입 텍스트 표시)
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    SNSTheme {
        LoginScreen(
            id = "Taewook",
            password = "Park",
            onIdChange = {},
            onPasswordChange = {},
            onNavigateToSignUpScreen = {},
            onLoginClick = {}
        )
    }
}
