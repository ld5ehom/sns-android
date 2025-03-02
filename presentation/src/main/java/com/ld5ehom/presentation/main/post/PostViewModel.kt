package com.ld5ehom.presentation.main.post

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import com.ld5ehom.domain.model.Image
import com.ld5ehom.domain.usecase.main.post.GetImageListUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

// ViewModel for managing post-related image selection and UI state
// 게시물 관련 이미지 선택 및 UI 상태를 관리하는 ViewModel
@HiltViewModel
class PostViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModel(),
    ContainerHost<PostState, PostSideEffect> {

    // Container that holds the UI state and handles side effects
    // UI 상태를 저장하고 사이드 이펙트를 처리하는 컨테이너
    override val container: Container<PostState, PostSideEffect> = container(
        initialState = PostState(),
        buildSettings = {
            // Exception handler to catch errors in coroutines
            // 코루틴에서 발생하는 예외를 처리하는 핸들러
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(PostSideEffect.Toast(throwable.message.orEmpty())) }
            }
        }
    )

    init {
        // Load image list when ViewModel is initialized
        // ViewModel이 초기화될 때 이미지 목록 불러오기
        load()
    }

    private fun load() = intent {
        // Retrieve image list from the use case
        // 유스 케이스에서 이미지 목록을 가져옴
        val images = getImageListUseCase()
        reduce {
            // Set the first image as selected by default, if available
            // 첫 번째 이미지를 기본적으로 선택된 상태로 설정 (없으면 빈 리스트)
            state.copy(
                selectedImages = images.firstOrNull()?.let { listOf(it) } ?: emptyList(),
                images = images
            )
        }
    }

    fun onItemClick(image: Image) = intent {
        reduce {
            // Toggle image selection
            // 이미지 선택 상태를 토글
            if (state.selectedImages.contains(image)) {
                state.copy(
                    selectedImages = state.selectedImages - image
                )
            } else {
                state.copy(
                    selectedImages = state.selectedImages + image
                )
            }
        }
    }
}

// Immutable data class representing the UI state for image selection
// 이미지 선택을 위한 UI 상태를 나타내는 불변 데이터 클래스
@Immutable
data class PostState(
    val selectedImages: List<Image> = emptyList(), // Selected images list // 선택된 이미지 목록
    val images: List<Image> = emptyList() // List of all available images // 모든 사용 가능한 이미지 목록
)

// Sealed interface defining possible side effects in PostViewModel
// PostViewModel에서 발생할 수 있는 사이드 이펙트를 정의하는 sealed 인터페이스
sealed interface PostSideEffect {
    // Side effect for displaying a toast message
    // 토스트 메시지를 표시하는 사이드 이펙트
    class Toast(val message: String) : PostSideEffect
}
