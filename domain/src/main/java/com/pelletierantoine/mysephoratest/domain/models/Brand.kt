package com.pelletierantoine.mysephoratest.domain.models

enum class Brand(
    val id: String,
    val entireName: String
) {
    SEPHORA(
        id = "SEPHO",
        entireName = "SEPHORA COLLECTION"
    ),

    CHANNEL(
        id = "CHANN",
        entireName = "CHANNEL"
    ),

    UNKNOWN(
        id = "UNKN",
        entireName = "UNKNOWN"
    );

    companion object {
        fun fromId(id: String): Brand {
            return entries.find { it.id == id } ?: UNKNOWN
        }
    }
}