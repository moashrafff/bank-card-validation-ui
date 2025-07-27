package com.moashraf.cardvalidationengine.model

sealed class ExpiryValidationResult {
    object Valid : ExpiryValidationResult()
    object InvalidFormat : ExpiryValidationResult()
    object InvalidMonth : ExpiryValidationResult()
    object Expired : ExpiryValidationResult()
}