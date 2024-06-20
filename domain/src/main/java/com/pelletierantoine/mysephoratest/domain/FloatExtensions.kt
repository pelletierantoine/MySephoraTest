package com.pelletierantoine.mysephoratest.domain

import java.text.NumberFormat
import java.util.Locale

fun Float.toFormattedPrice(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return format.format(this)
}