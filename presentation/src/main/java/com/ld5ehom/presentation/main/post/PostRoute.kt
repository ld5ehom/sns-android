package com.ld5ehom.presentation.main.post

// This enumeration defines the routes for post-related screens
// 게시물 관련 화면으로 이동하기 위한 경로를 정의하는 enum 클래스
enum class PostRoute(val route: String) {

    // Represents the route for the image selection screen
    // 이미지 선택 화면으로 이동하기 위한 경로
    IMAGE_SELECT_SCREEN("ImageSelectScreen"),

    // Represents the route for the post screen
    // 게시물 작성 화면으로 이동하기 위한 경로
    POST_SCREEN("PostScreen"),
}
