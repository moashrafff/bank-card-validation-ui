package com.bankcardvalidator.cvvValidationEngine

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult

fun isCvvValid(cardNumber: String, cvv: String): CvvValidationResult = when {
    !cvv.all { it.isDigit() } -> CvvValidationResult.InvalidFormat
    CardBrandRules.getCvvLength(cardNumber) == null || cvv.length != CardBrandRules.getCvvLength(
        cardNumber
    ) -> CvvValidationResult.InvalidLength

    else -> CvvValidationResult.Valid
}