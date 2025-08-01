package com.bankcardvalidator.cvvValidationEngine.result

sealed class CvvValidationResult {
    data object Valid : CvvValidationResult()
    data object InvalidLength : CvvValidationResult()
    data object InvalidFormat : CvvValidationResult()
}