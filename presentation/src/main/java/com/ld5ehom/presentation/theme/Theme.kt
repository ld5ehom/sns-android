package com.ld5ehom.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = Color.White,
    primaryContainer = primaryContainer,
    onPrimaryContainer = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    background = Color.Black,
    onBackground = Color.White
)

@Composable
fun SNSTheme(
    content: @Composable () -> Unit
) {
    // Determine if the system is in dark mode (시스템이 다크 모드인지 확인)
    val darkTheme: Boolean = isSystemInDarkTheme()

    // Define the color scheme to be used in the theme (테마에 사용할 색상 팔레트를 정의)
    val colorScheme = ColorScheme

    // Access the current view to customize the window appearance (현재 뷰에 접근하여 윈도우 외관을 커스터마이즈)
    val view = LocalView.current
    if (!view.isInEditMode) {
        // Change status bar color and appearance based on theme (테마에 따라 상태 표시줄의 색상 및 외관 변경)
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // Apply the MaterialTheme with the defined color scheme and typography (정의된 색상 팔레트와 타이포그래피를 사용해 MaterialTheme 적용)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
