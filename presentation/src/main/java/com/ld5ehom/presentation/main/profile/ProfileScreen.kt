package com.ld5ehom.presentation.main.profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ld5ehom.presentation.component.CustomProfileImage
import com.ld5ehom.presentation.login.LoginActivity
import com.ld5ehom.presentation.theme.SNSTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
// Displays the Profile screen and handles the logic for navigation and logout
// (설정 화면을 표시하고 네비게이션 및 로그아웃 로직을 처리)
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    // Handles side effects such as showing a toast message or navigating to the login screen
    // (토스트 메시지 표시나 로그인 화면으로의 네비게이션 같은 부수 효과를 처리)
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ProfileSideEffect.Toast -> Toast.makeText(
                context,
                sideEffect.message,
                Toast.LENGTH_SHORT
            ).show()

            ProfileSideEffect.NavigateToLoginActivity -> {
                context.startActivity(
                    Intent(context, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
        }
    }

    // Displays the user's Profiles screen with a profile image, username, and logout button
    // (프로필 이미지, 사용자 이름, 로그아웃 버튼을 포함한 설정 화면을 표시)
    ProfileScreen(
        username = state.username,
        profileImageUrl = state.profileImageUrl,
        onImageChangeClick = {},
        onNameChangeClick = {},
        onLogoutClick = viewModel::onLogoutClick
    )
}

// Composable function to display the Profiles UI
// (설정 UI를 표시하는 컴포저블 함수)
@Composable
private fun ProfileScreen(
    username: String = "",
    profileImageUrl: String?,
    onImageChangeClick: () -> Unit,
    onNameChangeClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            // Displays the profile image
            CustomProfileImage(
                modifier = Modifier.size(150.dp),
                profileImageUrl = profileImageUrl,
            )

            // Button to change the profile image (프로필 이미지를 변경하는 버튼)
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onImageChangeClick
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                        .background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp),
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Displays the username and allows for name change on click
        // (사용자 이름을 표시하고 클릭 시 이름 변경 가능)
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onNameChangeClick() },
            text = username,
            style = MaterialTheme.typography.headlineMedium
        )

        // Logout button
        Button(
            modifier = Modifier.padding(top = 25.dp),
            onClick = onLogoutClick
        ) {
            Text("Log out")
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    SNSTheme {
        Surface {
            ProfileScreen(
                username = "Taewook",
                profileImageUrl = null,
                onImageChangeClick = {},
                onNameChangeClick = {},
                onLogoutClick = {}
            )
        }
    }
}
