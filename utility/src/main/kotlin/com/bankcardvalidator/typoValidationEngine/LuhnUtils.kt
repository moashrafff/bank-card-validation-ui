package com.bankcardvalidator.typoValidationEngine

internal fun isLuhnValid(cardNumber: String): Boolean {
    val reversedNumber = cardNumber.filter { it.isDigit() }.reversed()
    val totalSum = reversedNumber.mapIndexed { index, digit ->
        val value = digit.digitToInt() * (if (index % 2 == 0) 1 else 2)
        if (value > 9) value - 9 else value
    }.sum()
    return totalSum % 10 == 0
}