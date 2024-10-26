package com.ld5ehom.domain.usecase.file

import com.ld5ehom.domain.model.Image

interface UploadImageUseCase {
    suspend operator fun invoke(
        image: Image
    ): Result<String>
}
