package com.bankcardvalidator.cardNumberValidationEngine

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules
import com.bankcardvalidator.cardNumberValidationEngine.result.CardNumberValidationResult
import com.bankcardvalidator.typoValidationEngine.isLuhnValid

internal fun isCardNumberValid(cardNumber: String): CardNumberValidationResult = when {
    !cardNumber.all { it.isDigit() } -> CardNumberValidationResult.InvalidFormat
    CardBrandRules.getRule(cardNumber) == null -> CardNumberValidationResult.UnknownCardBrand
    !isLuhnValid(cardNumber) -> CardNumberValidationResult.InvalidLuhn
    else -> CardNumberValidationResult.Valid
}