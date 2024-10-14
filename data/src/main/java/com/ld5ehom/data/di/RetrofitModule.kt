package com.ld5ehom.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import com.ld5ehom.data.retrofit.UserService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

// local Host (TP ip)
val ld5ehom_HOST = "http://131.179.156.5:8080"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    // Provides and binds the OkHttpClient instance to the Dagger dependency graph
    // (OkHttpClient 인스턴스를 Dagger 의존성 그래프에 제공하고 바인딩함)
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    // Provides the Retrofit instance configured with a JSON converter and OkHttpClient (JSON 변환기와 OkHttpClient가 설정된 Retrofit 인스턴스 제공)
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        // Converter for serializing and deserializing JSON, ignoring unknown keys (알 수 없는 키를 무시하는 JSON 직렬화/역직렬화 변환기)
        val converterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json; charset=UTF8".toMediaType())  // Sets the content type to JSON

        return Retrofit.Builder()
            .baseUrl("${ld5ehom_HOST}/api/")  // Sets the base URL for the API (API의 기본 URL 설정)
            .addConverterFactory(converterFactory)  // Adds the JSON converter to Retrofit (JSON 변환기를 Retrofit에 추가)
            .client(client)  // Attaches OkHttpClient to handle HTTP requests (HTTP 요청을 처리하기 위해 OkHttpClient 연결)
            .build()
    }

    // Provides the UserService instance by creating it through Retrofit
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {  // Creates and binds UserService using Retrofit for API requests
        return retrofit.create(UserService::class.java)
    }
}
