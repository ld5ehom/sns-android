package com.ld5ehom.data.retrofit

import com.ld5ehom.data.model.CommonResponse
import com.ld5ehom.data.model.FileDTO
import okhttp3.MultipartBody
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * - Defines the API for uploading files with multipart form data.
 * - 멀티파트 폼 데이터를 사용하여 파일을 업로드하는 API 정의.
 */
interface FileService {

    /**
     * Upload File
     * - Uploads a file using multipart/form-data. (멀티파트/form-data를 사용하여 파일을 업로드.)
     * - Accepts the file name and the actual file as parts. (파일 이름과 실제 파일을 파트로 전달.)
     * - Returns a common response containing file details. (파일 세부 정보를 포함하는 공통 응답 반환.)
     *
     * @param fileName The name of the file being uploaded, sent as a multipart form-data part. (업로드할 파일의 이름, 멀티파트 폼 데이터로 전송.)
     * @param file The file being uploaded, sent as a multipart form-data part.(업로드할 파일, 멀티파트 폼 데이터로 전송.)
     * @return CommonResponse<FileDTO> A response containing the details of the uploaded file. (업로드된 파일 세부 정보를 포함한 응답.)
     */
    @POST("files")
    @Multipart
    @Headers("ContentType: multipart/form-data;")
    suspend fun uploadFile(
        @Part fileName: MultipartBody.Part,
        @Part file: MultipartBody.Part
    ): CommonResponse<FileDTO>
}
