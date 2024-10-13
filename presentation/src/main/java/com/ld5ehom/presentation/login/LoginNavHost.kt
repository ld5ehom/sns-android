package com.ld5ehom.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost() {
    // Create a navigation controller to manage navigation between composables
    // 컴포저블 간의 네비게이션을 관리하기 위한 네비게이션 컨트롤러 생성
    val navController = rememberNavController()

    // Define the NavHost which hosts the navigation graph
    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.name, // Start with WelcomeScreen as the default screen (기본 화면을 WelcomeScreen으로 설정)
    ) {

        // Define the composable for WelcomeScreen
        composable(route = LoginRoute.WelcomeScreen.name) {
            WelcomeScreen(
                onNavigateToLoginScreen = {
                    // Navigate to LoginScreen when the button is clicked
                    navController.navigate(route = LoginRoute.LoginScreen.name)
                }
            )
        }

        // Define the composable for LoginScreen
        composable(route = LoginRoute.LoginScreen.name) {
            // Show the login screen (로그인 화면 표시)
            LoginScreen()
        }

        // Define the composable for SignUpScreen
        composable(route = LoginRoute.SignUpScreen.name) {
            SignUpScreen(
                id = "ld5ehom",
                username = "Taewook",
                password1 = "206128807",
                password2 = "206128807",
                onIdChange = {},
                onUsernameChange = {},
                onPassword1Change = {},
                onPassword2Change = {}
            ) {
                // Add actions for sign-up click if necessary (회원가입 버튼 클릭 시 추가 작업)
            }
        }
    }
}
