package com.ld5ehom.domain.usecase.file

import com.ld5ehom.domain.model.Image

interface GetImageUseCase {
    operator fun invoke(
        contentUri: String
    ): Image?
}