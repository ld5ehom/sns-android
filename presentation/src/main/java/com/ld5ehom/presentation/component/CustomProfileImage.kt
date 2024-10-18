package com.ld5ehom.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

// Composable function that displays a circular profile image with a rainbow gradient border
// (무지개 그라데이션 테두리가 있는 원형 프로필 이미지를 표시하는 컴포저블 함수)
@Composable
fun CustomProfileImage(
    modifier: Modifier,  // Modifier for customizing layout and styling
    profileImageUrl: String? = null,  // Optional URL for the profile image, defaults to a person icon if null
    borderWidth: Dp = 4.dp
) {

    // Box layout to stack image and border (이미지와 테두리를 겹쳐서 배치하기 위한 Box 레이아웃)
    Box {
        // Defines the rainbow gradient brush for the border (테두리를 위한 무지개 그라데이션 브러시 정의)
        val rainbowColorsBrush = remember {
            Brush.sweepGradient(
                listOf(
                    Color(0xFF9575CD),
                    Color(0xFFBA68C8),
                    Color(0xFFE57373),
                    Color(0xFFFFB74D),
                    Color(0xFFFFF176),
                    Color(0xFFAED581),
                    Color(0xFF4DD0E1),
                    Color(0xFF9575CD)
                )
            )
        }

        // Displays the profile image or a default icon if the image URL is null
        // (프로필 이미지를 표시하거나 이미지 URL이 없으면 기본 아이콘 표시)
        Image(
            modifier = modifier
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),  // Apply rainbow gradient border
                    CircleShape  // Circle shape for the border
                )
                .padding(borderWidth)
                .clip(CircleShape),  // Clip the image to a circle
            painter = profileImageUrl?.let {
                rememberAsyncImagePainter(
                    model = profileImageUrl,  // Load image from the provided URL
                    contentScale = ContentScale.Crop  // Crop the image to fit
                )
            } ?: rememberVectorPainter(image = Icons.Filled.Person),  // If no URL, use default person icon
            colorFilter = if (profileImageUrl == null) ColorFilter.tint(Color.White) else null,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,  // Crop the content to fit the circle
        )
    }
}
