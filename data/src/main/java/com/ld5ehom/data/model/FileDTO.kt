package com.ld5ehom.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    val id:Long,
    val fileName:String,
    val createdAt:String,
    val filePath:String
)