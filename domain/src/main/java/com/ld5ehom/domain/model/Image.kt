package com.ld5ehom.domain.model

import kotlinx.serialization.Serializable

@Serializable
// Data class representing an image with its URI, name, size, and MIME type
data class Image(
    val uri: String,      // The URI of the image
    val name: String,     // The file name of the image
    val size: Long,       // The size of the image file in bytes
    val mimeType: String  // The MIME type of the image (e.g., image/jpeg, image/png)
)
