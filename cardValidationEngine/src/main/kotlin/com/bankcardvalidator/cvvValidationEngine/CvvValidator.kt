package com.bankcardvalidator.cvvValidationEngine

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult

internal fun isCvvValid(cardNumber: String?, cvv: String): CvvValidationResult = when {
    !cvv.all { it.isDigit() } -> CvvValidationResult.InvalidFormat

    else -> {
        val expectedLength = CardBrandRules.getCvvLength(cardNumber) ?: 3
        if (CardBrandRules.getCvvLength(cardNumber) == null || cvv.length != expectedLength)
            CvvValidationResult.InvalidLength
        else
            CvvValidationResult.Valid
    }

}

