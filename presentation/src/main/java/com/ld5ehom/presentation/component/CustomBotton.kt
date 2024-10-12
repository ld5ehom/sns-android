package com.ld5ehom.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
// Custom button with configurable text, click action, and modifier
// 텍스트, 클릭 동작, modifier를 설정할 수 있는 커스텀 버튼
fun CustomButton(
    modifier: Modifier = Modifier, // Default modifier for layout customization (레이아웃 커스터마이징을 위한 기본 Modifier)
    text: String, // Button text
    onClick: () -> Unit // Click event handler
) {
    // Button component with custom height, shape, and colors
    // 커스텀 높이, 모양, 색상을 가진 버튼 컴포넌트
    Button(
        modifier = modifier.height(48.dp), // Set button height to 48dp
        shape = RoundedCornerShape(8.dp), // Apply rounded corners with 8dp radius
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Set button background color
            contentColor = MaterialTheme.colorScheme.onPrimary // Set text color
        ),
        onClick = onClick // Action to perform on button click (버튼 클릭 시 실행될 동작)
    ) {
        // Display text inside the button
        Text(
            text = text, // Text displayed on the button
            style = MaterialTheme.typography.bodyLarge, // Apply bodyLarge typography style (bodyLarge 타이포그래피 스타일 적용)
            color = MaterialTheme.colorScheme.onPrimary // Set text color to contrast with the button background (버튼 배경색과 대비되는 텍스트 색상 설정)
        )
    }
}
