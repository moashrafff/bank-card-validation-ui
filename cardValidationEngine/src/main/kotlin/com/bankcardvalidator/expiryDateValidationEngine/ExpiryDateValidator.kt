package com.bankcardvalidator.expiryDateValidationEngine

import com.bankcardvalidator.result.ExpiryValidationResult
import java.util.Calendar


internal fun isExpiryDateValid(expiryDate: String): ExpiryValidationResult {
    val cleaned = expiryDate.replace("[^\\d]".toRegex(), "")
    if (cleaned.length != 4) return ExpiryValidationResult.InvalidFormat

    val month = cleaned.substring(0, 2).toIntOrNull() ?: return ExpiryValidationResult.InvalidFormat
    val year = cleaned.substring(2, 4).toIntOrNull() ?: return ExpiryValidationResult.InvalidFormat
    if (month !in 1..12) return ExpiryValidationResult.InvalidMonth

    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
    val currentYear = calendar.get(Calendar.YEAR) % 100 // Two-digit year
    val fullCurrentYear = calendar.get(Calendar.YEAR)   // Full year, e.g., 2025

    val fullExpiryYear = 2000 + year

    val maxAllowedYear = fullCurrentYear + 20
    if (fullExpiryYear > maxAllowedYear) {
        return ExpiryValidationResult.TooFar
    }

    return if (year < currentYear || (year == currentYear && month < currentMonth)) {
        ExpiryValidationResult.Expired
    } else {
        ExpiryValidationResult.Valid
    }
}