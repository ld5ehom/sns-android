package com.ld5ehom.presentation.main.post

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PostNavHost() {

    // Create a NavController to handle navigation in the application
    // 앱 내에서 네비게이션을 담당할 NavController 생성
    val navController = rememberNavController()

    // Obtain a shared ViewModel instance for passing data between screens
    // 화면 간 데이터를 주고받기 위해 공유할 ViewModel 인스턴스 가져오기
    val sharedViewModel: PostViewModel = viewModel()

    // Set up the navigation host with the NavController.
    // The startDestination is where the navigation begins.
    // NavController를 사용해 네비게이션 호스트를 설정.
    // startDestination은 네비게이션이 시작되는 화면을 의미함
    NavHost(
        navController = navController,
        startDestination = PostRoute.IMAGE_SELECT_SCREEN.route,
    ) {

        // Define the composable for the image selection screen
        // 이미지 선택 화면을 위한 컴포저블 정의
        composable(
            route = PostRoute.IMAGE_SELECT_SCREEN.route
        ) {
            // Pass the shared ViewModel to the ImageSelectScreen
            // 공유된 ViewModel을 ImageSelectScreen에 전달
            ImageSelectScreen(
                viewModel = sharedViewModel
            )
        }

        // Define the composable for the post screen
        // 게시물 작성 화면을 위한 컴포저블 정의
        composable(
            route = PostRoute.POST_SCREEN.route
        ) {
            // Implement your UI and logic for the post screen here
            // 게시물 작성 화면의 UI와 로직을 여기에 구현
        }
    }
}
