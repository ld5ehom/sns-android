package com.ld5ehom.data.model

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
