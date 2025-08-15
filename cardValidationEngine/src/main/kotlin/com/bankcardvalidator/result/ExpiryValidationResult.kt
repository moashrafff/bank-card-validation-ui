package com.bankcardvalidator.result

sealed class ExpiryValidationResult {
    object Valid : ExpiryValidationResult()
    object InvalidFormat : ExpiryValidationResult()
    object InvalidMonth : ExpiryValidationResult()
    object Expired : ExpiryValidationResult()
}