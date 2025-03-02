package com.ld5ehom.presentation.main.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ld5ehom.domain.model.Image
import com.ld5ehom.presentation.theme.SNSTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ImageSelectScreen(
    viewModel: PostViewModel
) {
    // Collect UI state from the ViewModel
    // ViewModel에서 UI 상태를 수집
    val state = viewModel.collectAsState().value

    // Pass state values to ImageSelectScreen
    // 상태 값을 ImageSelectScreen에 전달
    ImageSelectScreen(
        selectedImages = state.selectedImages,
        images = state.images,
        onBackClick = {},
        onNextClick = {},
        onItemClick = viewModel::onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageSelectScreen(
    selectedImages: List<Image>,
    images: List<Image>,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onItemClick: (Image) -> Unit
) {
    // Provide a surface to hold the UI (UI를 감싸는 Surface 제공)
    Surface {
        // Scaffold with top bar and content layout
        // 상단 바와 콘텐츠 레이아웃을 포함한 Scaffold
        Scaffold(
            topBar = {
                // Top app bar with title and navigation buttons
                // 제목과 네비게이션 버튼이 포함된 상단 앱 바
                CenterAlignedTopAppBar(
                    title = {
                        // Display screen title (화면 제목 표시)
                        Text(
                            text = "New Post",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    navigationIcon = {
                        // Back button (뒤로 가기 버튼)
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        // Next button
                        TextButton(onClick = onNextClick) {
                            Text(text = "Next")
                        }
                    }
                )
            },

            content = { paddingValues ->
                // Column layout for selected image preview and image grid
                // 선택한 이미지 미리보기 및 이미지 그리드를 포함하는 Column 레이아웃
                Column(modifier = Modifier.padding(paddingValues)) {
                    // Display the last selected image (마지막으로 선택한 이미지 표시)
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = rememberAsyncImagePainter(
                                model = selectedImages.lastOrNull()?.uri
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        // Show a message if no images are selected
                        // 선택된 이미지가 없을 경우 메시지 표시
                        if (images.isEmpty()) {
                            Text(text = "No selected images.")
                        }
                    }
                    // Grid layout to display available images
                    // 선택 가능한 이미지를 표시하는 그리드 레이아웃
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.White),
                        columns = GridCells.Adaptive(110.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        items(
                            count = images.size,
                            key = { index -> images[index].uri }
                        ) { index ->
                            val image = images[index]
                            // Clickable box to select/deselect an image
                            // 이미지를 선택/해제할 수 있는 클릭 가능한 Box
                            Box(
                                modifier = Modifier.clickable {
                                    onItemClick(image)
                                }
                            ) {
                                // Display image with square aspect ratio
                                // 정사각형 비율로 이미지 표시
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                    painter = rememberAsyncImagePainter(
                                        model = image.uri,
                                        contentScale = ContentScale.Crop
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                // Show check icon if image is selected
                                // 이미지가 선택된 경우 체크 아이콘 표시
                                if (selectedImages.contains(image)) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 4.dp, top = 4.dp)
                                            .clip(CircleShape)
                                            .background(color = Color.White),
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun ImageSelectScreenPreview() {
    SNSTheme {
        ImageSelectScreen(
            selectedImages = emptyList(),
            images = emptyList(),
            onBackClick = {},
            onNextClick = {},
            onItemClick = {},
        )
    }
}
