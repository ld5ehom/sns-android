package com.ld5ehom.data.usecase.file

import android.content.Context
import android.net.Uri
import com.ld5ehom.domain.usecase.file.GetInputStreamUseCase
import java.io.InputStream
import javax.inject.Inject

// This class implements the GetInputStreamUseCase interface and provides a method to open an InputStream from a content URI.
// 이 클래스는 GetInputStreamUseCase 인터페이스를 구현하며, 콘텐츠 URI에서 InputStream을 여는 메서드를 제공합니다.
class GetInputStreamUseCaseImpl @Inject constructor(
    private val context: Context  // Application context used to access content resolver (콘텐츠 리졸버에 접근하기 위한 애플리케이션 컨텍스트)
) : GetInputStreamUseCase {

    // Fetches the InputStream from the given content URI (주어진 콘텐츠 URI에서 InputStream을 가져옴)
    override fun invoke(contentUri: String): Result<InputStream> = kotlin.runCatching {
        val uri = Uri.parse(contentUri)  // Parse the content URI
        context.contentResolver.openInputStream(uri)  // Open InputStream using content resolver (콘텐츠 리졸버를 사용해 InputStream 열기)
            ?: throw IllegalStateException("Failed to obtain InputStream")  // If InputStream is null, throw an exception
    }
}
