package com.ld5ehom.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ld5ehom.presentation.component.CustomButton
import com.ld5ehom.presentation.component.CustomTextField
import com.ld5ehom.presentation.theme.SNSTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),  // SignUpViewModel provided by Hilt
    onNavigateToLoginScreen: () -> Unit  // Lambda function to handle navigation to the login screen
) {
    // Collect the current state from the ViewModel
    val state = viewModel.collectAsState().value

    // Get the current context for displaying Toast messages
    val context = LocalContext.current

    // Handle side effects such as showing Toast messages or navigating to the login screen
    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is SignUpSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            SignUpSideEffect.NavigateToLoginScreen -> onNavigateToLoginScreen()  // Navigate to the login screen on successful sign-up
        }
    }

    // Render the SignUpScreen with the current state and event handlers
    SignUpScreen(
        id = state.id,
        username = state.username,
        password1 = state.password,
        password2 = state.repeatPassword,
        onIdChange = viewModel::onIdChange,
        onUsernameChange = viewModel::onUsernameChange,
        onPassword1Change = viewModel::onPasswordChange,
        onPassword2Change = viewModel::onRepeatPasswordChange,
        onSignUpClick = viewModel::onSignUpClick
    )
}

@Composable
fun SignUpScreen(
    id: String,
    username: String,
    password1: String,
    password2: String, // Check password

    // Handles changes to ID, username, and passwords (used for input change callbacks)
    // ID, 사용자 이름, 패스워드 변경을 처리하는 콜백 함수 (입력 변경 처리용)
    onIdChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPassword1Change: (String) -> Unit,
    onPassword2Change: (String) -> Unit,

    // Triggered when the sign-up button is clicked  (회원가입 버튼이 클릭될 때 호출)
    onSignUpClick: () -> Unit
) {
    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

            // Input section for sign in form
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "Create an account",
                    style = MaterialTheme.typography.headlineMedium
                )

                // ID
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Id",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueChange = onIdChange
                )

                // User Name
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "User name",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = username,
                    onValueChange = onUsernameChange
                )

                // Password
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Password",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = password1,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onPassword1Change
                )

                // Repeat password
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Repeat password",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = password2,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onPassword2Change
                )

                // Sign up button
                CustomButton(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    text = "Sign up",
                    onClick = onSignUpClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SNSTheme {
        SignUpScreen(
            id = "ld5ehom",
            username = "Taewook",
            password1 = "206-128-807",
            password2 = "206-128-807",
            onIdChange = {},
            onUsernameChange = {},
            onPassword1Change = {},
            onPassword2Change = {},
            onSignUpClick = {})
    }
}