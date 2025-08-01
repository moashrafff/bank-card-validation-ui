package com.bankcardvalidator.result

sealed class CardNumberValidationResult {
    data object Valid : CardNumberValidationResult()
    data object InvalidFormat : CardNumberValidationResult()
    data object InvalidLuhn : CardNumberValidationResult()
    data object UnknownCardBrand : CardNumberValidationResult()
}