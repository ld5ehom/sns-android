package com.ld5ehom.data.retrofit

import android.util.Log
import com.ld5ehom.domain.usecase.file.GetInputStreamUseCase
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.FileNotFoundException
import okio.source

// A custom RequestBody class that handles reading a file from a content URI and writing it to a BufferedSink for multipart upload.
// 파일을 콘텐츠 URI에서 읽고 BufferedSink로 멀티파트 업로드를 처리하는 커스텀 RequestBody 클래스.
class UriRequestBody constructor(
    val contentUri: String,  // The URI of the content to be uploaded (업로드할 콘텐츠의 URI)
    val getInputStreamUseCase: GetInputStreamUseCase,  // Use case for retrieving the input stream from the URI (URI에서 입력 스트림을 가져오는 유즈케이스)
    val contentType: MediaType? = null,  // The MIME type of the content
    val contentLength: Long,  // The size of the content
) : RequestBody() {

    // Returns the MIME type of the content (콘텐츠의 MIME 타입 반환)
    override fun contentType(): MediaType? {
        return contentType
    }

    // Returns the size of the content (콘텐츠의 크기 반환)
    override fun contentLength(): Long {
        return contentLength
    }

    // Writes the content to the provided BufferedSink (제공된 BufferedSink로 콘텐츠를 작성)
    override fun writeTo(sink: BufferedSink) {
        try {
            // Fetch the input stream from the URI and write to the sink (URI에서 입력 스트림을 가져와 sink로 작성)
            getInputStreamUseCase(
                contentUri = contentUri
            )
                .getOrThrow()
                .use { inputStream ->
                    sink.writeAll(inputStream.source())  // Write the entire input stream to the sink (전체 입력 스트림을 sink에 작성)
                }
        } catch (e: FileNotFoundException) {
            Log.e("UriRequestBody", e.message.orEmpty())  // Log any FileNotFoundException (FileNotFoundException 로그 기록)
        }
    }
}
