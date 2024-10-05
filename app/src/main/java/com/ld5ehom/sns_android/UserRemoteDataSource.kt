package com.ld5ehom.sns_android

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody.Companion.toRequestBody

// Defines a data source for network communication
class UserRemoteDataSource constructor(
    private val service: LoginRetrofitService // Instance of Retrofit service for login
) {
    // Takes user ID and password as input, processes login request, and returns the result
    // 사용자 ID와 패스워드를 입력받아 로그인 요청을 처리하고 결과를 반환함
    // Creates a LoginParam entity, converts it into a request body, and communicates using the service
    // LoginParam 엔티티를 생성하여 요청 본문으로 전환 후, 서비스를 통해 통신 진행
    suspend fun login(id: String, pw: String): String? {
        return try {
            // Creates a LoginParam instance with ID and password, and encodes it to a JSON string
            // ID와 패스워드로 LoginParam 인스턴스 생성 및 JSON 문자열로 인코딩
            val param = Json.encodeToString(LoginParam(id, pw))
            // Converts encoded data to request body, sends login request, and returns response data
            // 인코딩된 데이터를 요청 본문으로 전환하여 로그인 요청 후, 응답 데이터 반환
            service.login(param.toRequestBody()).data
        } catch (e: Exception) {
            e.printStackTrace() // Prints the stack trace if an exception occurs (예외 발생 시 스택 트레이스 출력)
            null // Returns null if an exception occurs (예외 발생 시 null 반환)
        }
    }
}

