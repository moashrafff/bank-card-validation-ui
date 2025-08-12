package com.bankcardvalidator.api

import com.bankcardvalidator.api.CardValidator.isCardNumberValid
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult
import com.bankcardvalidator.result.CardNumberValidationResult
import com.bankcardvalidator.result.ExpiryValidationResult

object CardValidator {
    /**
     * Validates the CVV for the given card number.
     *
     * - Returns [CvvValidationResult.InvalidFormat] if the CVV contains non-digit characters.
     * - Returns [CvvValidationResult.InvalidLength] if the CVV length is incorrect.
     * - Returns [CvvValidationResult.Valid] if the CVV is in the correct format and length for the detected card brand.
     */
    fun isCvvValid(cardNumber: String?, cvv: String): CvvValidationResult =
        com.bankcardvalidator.cvvValidationEngine.isCvvValid(cardNumber = cardNumber, cvv = cvv)

    /**
     * Validates the given card number and returns the corresponding validation result.
     *
     * Delegates to [isCardNumberValid] which performs:
     * - Format check: ensures only digits are present
     * - Brand check: validates against known card brand rules
     * - Luhn algorithm check: ensures checksum is valid
     *
     * @param cardNumber The card number string to validate
     * @return [CardNumberValidationResult] indicating whether it's valid, invalid format, unknown brand, or invalid Luhn
     */
    fun isCardNumberValid(cardNumber: String): CardNumberValidationResult =
        com.bankcardvalidator.cardNumberValidationEngine.isCardNumberValid(cardNumber = cardNumber)

    /**
     * Validates a credit card expiry date string in MMYY format (e.g., "12/25", "1225", "12-25").
     *
     * The method performs the following checks:
     * - Removes all non-digit characters from the input
     * - Ensures the resulting string is exactly 4 digits (2 for month, 2 for year)
     * - Validates that the month is within 1–12
     * - Compares the expiry date against the current month/year to determine if it's expired
     *
     * @param expiryDate The expiry date string to validate
     * @return [ExpiryValidationResult] indicating whether the input is valid, expired, or has formatting/month errors
     */

    fun isExpiryDateValid(expiryDate: String): ExpiryValidationResult =
        com.bankcardvalidator.expiryDateValidationEngine.isExpiryDateValid(expiryDate = expiryDate)
}