package com.ld5ehom.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ld5ehom.presentation.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Custom text field for user input with customizable modifier, value, and onValueChange handler
// 사용자 입력을 위한 커스텀 텍스트 필드, modifier, value, onValueChange 처리기 설정 가능
fun CustomTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    // TextField component with custom styling and behavior
    // 커스텀 스타일 및 동작을 가진 TextField 컴포넌트
    TextField(
        modifier = modifier, // Modifier for customizing the field's layout (레이아웃을 커스터마이징할 Modifier)
        value = value, // Current value of the text field (텍스트 필드의 현재 값)
        onValueChange = onValueChange, // Handler for text changes (텍스트 변경을 처리하는 함수)

        // Custom text field colors for background and indicators
        // 배경 및 인디케이터에 대한 커스텀 색상 설정
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer, // Background color
            focusedIndicatorColor = Color.Transparent, // Remove the indicator when focused (포커스 상태일 때 인디케이터 제거)
            unfocusedIndicatorColor = Color.Transparent, // Remove the indicator when unfocused (포커스 해제 시 인디케이터 제거)
            disabledIndicatorColor = Color.Transparent, // Remove the indicator when disabled (비활성화 시 인디케이터 제거)
            textColor = darkestBlue // Set text color to gold
        ),

        // Apply rounded corners to the text field
        // 텍스트 필드에 둥근 모서리 적용
        shape = RoundedCornerShape(8.dp),
    )
}
