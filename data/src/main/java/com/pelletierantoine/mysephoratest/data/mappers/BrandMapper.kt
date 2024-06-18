package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.models.BrandEntity
import com.pelletierantoine.mysephoratest.domain.models.Brand

internal class BrandMapper : Mapper<BrandEntity, Brand> {
    override fun fromEntity(entity: Brand): BrandEntity {
        return BrandEntity(
            id = entity.id,
            name = entity.name
        )
    }

    override fun toEntity(data: BrandEntity): Brand {
        return Brand.fromId(data.id)
    }
}