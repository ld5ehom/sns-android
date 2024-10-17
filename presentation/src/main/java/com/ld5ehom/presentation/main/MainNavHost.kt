package com.ld5ehom.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ld5ehom.presentation.R
import com.ld5ehom.presentation.main.home.HomeScreen
import com.ld5ehom.presentation.main.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Main navigation host for the app's navigation setup
// (앱의 네비게이션 구성을 담당하는 메인 네비게이션 호스트)
fun MainNavHost() {
    // Remember the NavController to manage navigation between screens
    // (화면 간 네비게이션을 관리하는 NavController 생성)
    val navController = rememberNavController()

    Surface {
        Scaffold(
            topBar = {
                // TopAppBar to display the app's title (앱의 제목을 표시하는 TopAppBar)
                TopAppBar(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    title = {
                        Text(text = stringResource(id = R.string.app_name))  // Displays the app name from resources (앱 이름을 리소스에서 불러와 표시)
                    }
                )
            },
            content = { padding ->
                // Navigation host responsible for switching between composable screens
                // (컴포저블 화면 간 전환을 담당하는 네비게이션 호스트)
                NavHost(
                    modifier = Modifier.padding(padding),  // Applies padding to the content (컨텐츠에 패딩 적용)
                    navController = navController,  // Passes the NavController to manage navigation (네비게이션을 관리하는 NavController 전달)
                    startDestination = MainRoute.HOME.route  // Defines the start destination as Home (홈 화면 정의)
                ) {
                    // Composable screen for the "Home" route
                    composable(route = MainRoute.HOME.route) {
                        HomeScreen()
                    }
                    // Composable screen for the "Profile" route
                    composable(route = MainRoute.PROFILE.route) {
                        ProfileScreen()
                    }
                }
            },
            bottomBar = {
                // Bottom navigation bar to navigate between different screens
                MainBottomBar(
                    // Passes the NavController to handle bottom bar navigation
                    // (하단 바 네비게이션을 관리하기 위해 NavController 전달)
                    navController = navController
                )
            }
        )
    }
}
