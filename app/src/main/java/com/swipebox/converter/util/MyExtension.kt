package com.swipebox.converter.util

import com.swipebox.converter.data.model.RateItem

object MyExtension {
    fun convertMapToRateItems(map: Map<String, Double>): List<RateItem> {
        return map.map { RateItem(it.key, it.value) }
    }

    fun String.toFormattedDecimal(): String {
        val originalNumber = this.toDoubleOrNull() ?: return this

        val decimalPlaces = this.substringAfterLast('.', "").length
        val format = if (decimalPlaces > 3) "%.3f" else "%.${decimalPlaces}f"

        return String.format(format, originalNumber)
    }
}