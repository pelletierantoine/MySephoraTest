package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.models.ImageUrlsEntity
import com.pelletierantoine.mysephoratest.domain.models.ImageUrls

internal class ImageUrlsMapper : Mapper<ImageUrlsEntity, ImageUrls> {
    override fun fromEntity(entity: ImageUrls): ImageUrlsEntity {
        return ImageUrlsEntity(
            small = entity.small,
            large = entity.large
        )
    }

    override fun toEntity(data: ImageUrlsEntity): ImageUrls {
        return ImageUrls(
            small = data.small,
            large = data.large
        )
    }
}