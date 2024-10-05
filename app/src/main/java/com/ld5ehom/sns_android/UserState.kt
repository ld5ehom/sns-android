package com.ld5ehom.sns_android

/**
 * @author Taewook.ok
 */
// Defines the login status for a user
// 사용자의 로그인 상태를 정의하는 enum
enum class UserState {
    NONE,      // Initial state, no login attempt made yet // 초기 상태, 아직 로그인 시도가 이루어지지 않음
    FAILED,    // Login attempt failed // 로그인 시도 실패
    LOGGED_IN, // User is successfully logged in // 사용자가 성공적으로 로그인 됨
}
