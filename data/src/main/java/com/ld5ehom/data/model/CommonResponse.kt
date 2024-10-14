package com.ld5ehom.data.model

import kotlinx.serialization.Serializable

// Generic class to represent a common API response format
// (일반적인 API 응답 형식을 나타내는 제네릭 클래스)
@Serializable
data class CommonResponse<T>(
    val result: String,  // The result status of the request (요청의 결과 상태, 예: "success" 또는 "failure")
    val data: T,  // The actual data returned from the API (API에서 반환된 실제 데이터)
    val errorCode: String,
    val errorMessage: String,
)
