package com.ld5ehom.data.usecase.file

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.ld5ehom.domain.model.Image
import com.ld5ehom.domain.usecase.file.GetImageUseCase
import javax.inject.Inject

// This class implements the GetImageUseCase interface and retrieves image metadata from a given content URI.
// 이 클래스는 GetImageUseCase 인터페이스를 구현하며, 주어진 콘텐츠 URI에서 이미지 메타데이터를 가져옵니다.
class GetImageUseCaseImpl @Inject constructor(
    private val context: Context  // Application context used to access content resolver (콘텐츠 리졸버에 접근하기 위한 애플리케이션 컨텍스트)
) : GetImageUseCase {

    // Retrieves image metadata from the given content URI (주어진 콘텐츠 URI에서 이미지 메타데이터를 가져옴)
    override fun invoke(contentUri: String): Image? {
        val uri = Uri.parse(contentUri)  // Parse the content URI (콘텐츠 URI를 파싱)
        val projection = arrayOf(  // Define the columns to query from the media store (미디어 스토어에서 쿼리할 열 정의)
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
        )
        val cursor = context.contentResolver.query(  // Query the content resolver for the image data (콘텐츠 리졸버에 이미지 데이터 쿼리)
            uri,
            projection,
            null,
            null,
            null
        )

        return cursor?.use { c ->  // Use the cursor to extract image metadata (커서를 사용하여 이미지 메타데이터 추출)
            c.moveToNext()
            val idIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)  // Get the index for the image ID column (이미지 ID 열의 인덱스를 가져옴)
            val nameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)  // Get the index for the image name column (이미지 이름 열의 인덱스)
            val sizeIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)  // Get the index for the image size column (이미지 크기 열의 인덱스)
            val mimeTypeIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)  // Get the index for the MIME type column (MIME 타입 열의 인덱스)

            val name = cursor.getString(nameIndex)  // Extract the image name (이미지 이름 추출)
            val size = cursor.getLong(sizeIndex)  // Extract the image size (이미지 크기 추출)
            val mimeType = cursor.getString(mimeTypeIndex)  // Extract the MIME type (MIME 타입 추출)
            Image(
                uri = contentUri,  // Set the image URI (이미지 URI 설정)
                name = name,  // Set the image name (이미지 이름 설정)
                size = size,  // Set the image size (이미지 크기 설정)
                mimeType = mimeType  // Set the MIME type (MIME 타입 설정)
            )
        }
    }
}
