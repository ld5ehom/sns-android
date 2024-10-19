package com.ld5ehom.data.model

import com.ld5ehom.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
// A serializable data transfer object (DTO) designed for use with APIs.
data class UserDTO(
    val id: Long,
    val loginId: String,
    val userName: String,
    val extraUserInfo: String,
    val profileFilePath: String,
)

// Converts UserDTO to a domain-specific User model used for internal business logic.
// (UserDTO를 도메인 내에서 사용하는 User 모델로 변환하는 함수)
fun UserDTO.toDomainModel(): User {
    return User(
        // Maps the UserDTO fields to User fields. (UserDTO 필드를 User 모델 필드로 매핑)
        id = this.id,
        loginId = this.loginId,
        username = this.userName,
        profileImageUrl = this.profileFilePath
    )
}
