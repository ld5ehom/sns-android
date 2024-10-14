package com.ld5ehom.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Serializable
// Data class representing login parameters
// (로그인 시 필요한 파라미터를 나타내는 데이터 클래스)
data class LoginParam(
    val loginId: String,
    val password: String
) {

    // Converts the LoginParam object to a JSON RequestBody for API calls
    // (LoginParam 객체를 JSON 형식으로 변환하여 API 요청 본문으로 사용)
    fun toRequestBody(): RequestBody {
        return Json.encodeToString(this).toRequestBody()  // Serializes the object to JSON and converts it to RequestBody
    }
}
