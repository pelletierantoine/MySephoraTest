package com.pelletierantoine.mysephoratest.ui.extensions

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun Float.toFormattedPrice(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return format.format(this)
}