package com.ld5ehom.data.retrofit

import kotlinx.coroutines.runBlocking
import com.ld5ehom.data.UserDataStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Intercepts HTTP requests to dynamically add headers such as token and content type.
// (HTTP 요청을 가로채서 토큰 및 컨텐츠 타입과 같은 헤더를 동적으로 추가함)
class CustomInterceptor @Inject constructor(
    // Injected UserDataStore to retrieve the user's token. (사용자의 토큰을 가져오기 위한 UserDataStore 주입)
    private val userDataStore: UserDataStore
) : Interceptor {

    // Intercepts the chain of HTTP requests to add headers. (HTTP 요청 체인을 가로채서 헤더를 추가)
    override fun intercept(chain: Interceptor.Chain): Response {
        // Fetches the token using runBlocking to ensure it's retrieved before the request proceeds.
        // (요청이 진행되기 전에 토큰을 가져오기 위해 runBlocking 사용)
        val token: String? = runBlocking { userDataStore.getToken() }

        // Proceeds with the request, adding the token and content type headers.
        // (요청을 진행하면서 토큰 및 컨텐츠 타입 헤더 추가)
        return chain.proceed(
            chain.request()
                .newBuilder()
                .run {
                    // Adds the token to the header if it is not null or empty. (토큰이 null 또는 비어있지 않으면 헤더에 추가)
                    if (token.isNullOrEmpty()) {
                        this
                    } else {
                        this.addHeader("Token", token)
                    }
                }
                // Adds the Content-Type header for all requests.(모든 요청에 대해 Content-Type 헤더 추가)
                .addHeader("Content-Type", "application/json; charset=UTF8")
                .build()
        )
    }
}
