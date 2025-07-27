package com.bankcardvalidator.api

import com.bankcardvalidator.cardNumberValidationEngine.isCardNumberValid
import com.bankcardvalidator.cardNumberValidationEngine.result.CardNumberValidationResult
import com.bankcardvalidator.typoValidationEngine.isCvvValid

object CardTypoDetector {
    /**
     * Validates the CVV based on the detected card brand rules.
     * Returns true if the CVV is the correct length and format for the given card number.
     */
    fun isValidCvv(cardNumber: String, cvv: String): Boolean =
        isCvvValid(cardNumber = cardNumber, cvv = cvv)

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
    fun isValidCardNumber(cardNumber: String): CardNumberValidationResult =
        isCardNumberValid(cardNumber = cardNumber)
}