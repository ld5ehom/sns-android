package com.ld5ehom.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

// Data class to hold parameters for updating user information via the API
// (API를 통해 사용자 정보를 업데이트하기 위한 파라미터를 보유한 데이터 클래스)
@Serializable
data class UpdateMyInfoParam(
    val userName: String,
    val extraUserInfo: String,
    val profileFilePath: String, // Path to the user's profile image
) {
    // Converts the data class to a JSON request body for API communication
    // (API 통신을 위한 JSON 요청 본문으로 변환)
    fun toRequestBody(): RequestBody {
        return Json.encodeToString(this).toRequestBody() // Converts the object to JSON string and wraps it in a request body
    }
}
