package com.ld5ehom.data.usecase.file

import com.ld5ehom.data.retrofit.FileService
import com.ld5ehom.data.retrofit.UriRequestBody
import com.ld5ehom.domain.model.Image
import com.ld5ehom.domain.usecase.file.GetInputStreamUseCase
import com.ld5ehom.domain.usecase.file.UploadImageUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,  // Handles file upload to the server (파일을 서버에 업로드 처리)
    private val getInputStreamUseCase: GetInputStreamUseCase,  // Retrieves input stream for the image file (이미지 파일의 입력 스트림을 가져옴)
) : UploadImageUseCase {

    // Invokes the use case to upload the image (이미지 업로드 유즈케이스 실행)
    override suspend fun invoke(image: Image): Result<String> = kotlin.runCatching {
        // Prepare the file name part for the multipart form (멀티파트 폼을 위한 파일 이름 준비)
        val fileNamePart = MultipartBody.Part.createFormData(
            "fileName",  // Key for the file name
            image.name   // The actual file name
        )

        // Create the request body using the data/retrofit/UriRequestBody (UriRequestBody를 사용하여 요청 본문 생성)
        val requestBody = UriRequestBody(
            contentUri = image.uri,
            getInputStreamUseCase = getInputStreamUseCase,  // Use case to get the input stream (입력 스트림을 가져오는 유즈케이스)
            contentType = image.mimeType.toMediaType(),  // MIME type of the image (이미지의 MIME 타입)
            contentLength = image.size    // buffer
        )

        // Prepare the file part for the multipart form (멀티파트 폼을 위한 파일 파트 준비)
        val filePart = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestBody  // The request body created earlier
        )

        // Upload the file using the file service and return the file path (파일 서비스를 사용하여 파일 업로드 후 파일 경로 반환)
        fileService.uploadFile(
            fileName = fileNamePart,
            file = filePart
        ).data.filePath  // Return the file path from the response (응답에서 파일 경로 반환)
    }
}
