package com.pelletierantoine.mysephoratest.data.mappers

internal interface Mapper<D, E> {
    fun fromEntity(entity: E): D

    fun toEntity(data: D): E
}