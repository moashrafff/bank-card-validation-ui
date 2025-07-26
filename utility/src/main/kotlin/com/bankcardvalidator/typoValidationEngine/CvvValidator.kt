package com.bankcardvalidator.typoValidationEngine

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules

internal fun isCvvValid(cardNumber: String, cvv: String): Boolean {
    val expectedLength = CardBrandRules.getCvvLength(cardNumber) ?: return false
    val cleanedCvv=cvv.filter { it.isDigit() }
    return cleanedCvv.length == expectedLength
}