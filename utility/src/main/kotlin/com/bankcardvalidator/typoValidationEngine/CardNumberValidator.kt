package com.bankcardvalidator.typoValidationEngine

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules

internal fun isCardNumberValid(cardNumber: String): Boolean {
    val cleanedNumber = cardNumber.filter { it.isDigit() }
    val rule = CardBrandRules.getRule(cardNumber) ?: return false
    val isLengthValid = cleanedNumber.length in rule.validLengths
    val isLuhnValid = isLuhnValid(cleanedNumber)
    return isLengthValid && isLuhnValid
}