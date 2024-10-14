package com.ld5ehom.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

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
            LoginScreen(
                onNavigateToSignUpScreen = {
                    navController.navigate(LoginRoute.SignUpScreen.name)
                }
            )
        }

        // Define the composable for SignUpScreen
        composable(route = LoginRoute.SignUpScreen.name) {
            SignUpScreen(
                // Lambda function for navigating to the login screen after successful sign-up
                onNavigateToLoginScreen = {
                    // Use navController to navigate to the LoginScreen
                    navController.navigate(
                        route = LoginRoute.LoginScreen.name,
                        navOptions = navOptions {
                            // Removes the WelcomeScreen from the back stack to prevent navigating back to it
                            popUpTo(LoginRoute.WelcomeScreen.name)
                        }
                    )
                }
            )
        }

    }
}
