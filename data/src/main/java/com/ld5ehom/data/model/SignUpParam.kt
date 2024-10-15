package com.ld5ehom.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Serializable
// Data class representing the parameters for the sign-up API request
// (회원가입 API 요청을 위한 파라미터를 나타내는 데이터 클래스)
data class SignUpParam(
    val loginId:String,
    val name:String,
    val password:String,
    val extraUserInfo:String,
    val profileFilePath:String
){
    // Converts the SignUpParam object into a JSON RequestBody for the API request
    // (SignUpParam 객체를 JSON 요청 본문으로 변환)
    fun toRequestBody():RequestBody{
        return Json.encodeToString(this).toRequestBody()
    }
}