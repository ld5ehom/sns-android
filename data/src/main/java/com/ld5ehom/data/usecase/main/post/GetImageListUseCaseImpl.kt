package com.ld5ehom.data.usecase.main.post

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Images
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ld5ehom.domain.model.Image
import com.ld5ehom.domain.usecase.main.post.GetImageListUseCase
import javax.inject.Inject

//https://developer.android.com/about/versions/14/changes/partial-photo-video-access?hl=ko
// Implementation of GetImageListUseCase that retrieves a list of images from the device storage
// 기기 저장소에서 이미지 목록을 가져오는 GetImageListUseCase 구현체
class GetImageListUseCaseImpl @Inject constructor(
    private val context: Context
) : GetImageListUseCase {

    override suspend fun invoke(): List<Image> = withContext(Dispatchers.IO) {
        // Get content resolver for querying media store
        // 미디어 저장소를 조회하기 위한 ContentResolver 가져오기
        val contentResolver = context.contentResolver

        // Define the columns to retrieve from the media store
        // 미디어 저장소에서 가져올 열(컬럼) 정의
        val projection = arrayOf(
            Images.Media._ID,
            Images.Media.DISPLAY_NAME,
            Images.Media.SIZE,
            Images.Media.MIME_TYPE,
        )

        // Determine the URI for querying images based on Android version
        // 안드로이드 버전에 따라 이미지 조회를 위한 URI 결정
        val collectionUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Query all external volumes instead of primary storage only
            // 기본 저장소가 아닌 모든 외부 저장소에서 조회
            Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            Images.Media.EXTERNAL_CONTENT_URI
        }

        // Create a mutable list to store retrieved images
        // 조회된 이미지를 저장할 리스트 생성
        val images = mutableListOf<Image>()

        // Execute the query to retrieve images from the media store
        // 미디어 저장소에서 이미지를 가져오기 위한 쿼리 실행
        contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            "${Images.Media.DATE_ADDED} DESC"
        // Sort images by most recently added
        // 가장 최근에 추가된 이미지부터 정렬
        )?.use { cursor ->
            // Retrieve column indices (컬럼 인덱스 가져오기)
            val idColumn = cursor.getColumnIndexOrThrow(Images.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(Images.Media.SIZE)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(Images.Media.MIME_TYPE)

            // Iterate through the query results
            // 조회된 결과를 순회하며 이미지 목록 생성
            while (cursor.moveToNext()) {
                val uri = ContentUris.withAppendedId(collectionUri, cursor.getLong(idColumn))
                val name = cursor.getString(displayNameColumn)
                val size = cursor.getLong(sizeColumn)
                val mimeType = cursor.getString(mimeTypeColumn)

                // Create an Image object and add it to the list
                // Image 객체를 생성하여 리스트에 추가
                val image = Image(
                    uri = uri.toString(),
                    name = name,
                    size = size,
                    mimeType = mimeType
                )
                images.add(image)
            }
        }

        return@withContext images
    }
}
