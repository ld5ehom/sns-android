package com.ld5ehom.data.retrofit

import com.ld5ehom.data.model.CommonResponse
import com.ld5ehom.data.model.UserDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("users/login")
    @Headers("Content-Type:application/json; charset=UTF8") // for HTTP 500 error
    suspend fun login(
        @Body requestBody: RequestBody  // parameter
    ):CommonResponse<String>

    @POST("users/sign-up")
    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun signUp(
        @Body requestBody: RequestBody
    ):CommonResponse<Long>

    @GET("users/my-page")
    @Headers("Content-Type:application/json; charset=UTF8")
    suspend fun myPage():CommonResponse<UserDTO>

}