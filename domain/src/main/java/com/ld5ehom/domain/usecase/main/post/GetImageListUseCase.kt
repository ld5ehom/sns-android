package com.ld5ehom.domain.usecase.main.post

import com.ld5ehom.domain.model.Image

interface GetImageListUseCase {
    suspend operator fun invoke():List<Image>
}