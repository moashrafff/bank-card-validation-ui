package com.bankcardvalidator.api

import com.bankcardvalidator.cardNumberValidationEngine.isCardNumberValid
import com.bankcardvalidator.cardNumberValidationEngine.result.CardNumberValidationResult
import com.bankcardvalidator.cvvValidationEngine.isCvvValid
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult

object CardTypoDetector {
    /**
     * Validates the CVV for the given card number.
     *
     * - Returns [CvvValidationResult.InvalidFormat] if the CVV contains non-digit characters.
     * - Returns [CvvValidationResult.InvalidLength] if the CVV length is incorrect.
     * - Returns [CvvValidationResult.Valid] if the CVV is in the correct format and length for the detected card brand.
     */
    fun isValidCvv(cardNumber: String, cvv: String): CvvValidationResult =
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