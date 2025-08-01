package com.bankcardvalidator.api

import com.bankcardvalidator.model.ExpiryValidationResult
import java.util.Calendar

object ExpiryDateValidator {

    /**
     * Returns true if the expiry date is in a valid format (MM/YY or MMYY),
     * the month is between 1–12, and the date is not in the past.
     */
    fun validate(expiry: String): ExpiryValidationResult {
        val cleaned = expiry.replace("[^\\d]".toRegex(), "")
        if (cleaned.length != 4) return ExpiryValidationResult.InvalidFormat

        val month = cleaned.substring(0, 2).toIntOrNull() ?: return ExpiryValidationResult.InvalidFormat
        val year = cleaned.substring(2, 4).toIntOrNull() ?: return ExpiryValidationResult.InvalidFormat
        if (month !in 1..12) return ExpiryValidationResult.InvalidMonth

        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
        val currentYear = calendar.get(Calendar.YEAR) % 100 // Two-digit year

        return if (year < currentYear || (year == currentYear && month < currentMonth)) {
            ExpiryValidationResult.Expired
        } else {
            ExpiryValidationResult.Valid
        }
    }
}