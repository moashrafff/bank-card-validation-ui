package com.bankcardvalidator.api

import com.bankcardvalidator.typoValidationEngine.isCardNumberValid
import com.bankcardvalidator.typoValidationEngine.isCvvValid
import com.bankcardvalidator.typoValidationEngine.isLuhnValid

object CardTypoDetector {
    /**
     * Detects the card typo, structure.  based on card number and prevent input errors.
     * Returns true if the card number is valid.
     */
    fun isValidCard(cardNumber: String): Boolean = isLuhnValid(cardNumber = cardNumber)

    /**
     * Validates the CVV based on the detected card brand rules.
     * Returns true if the CVV is the correct length and format for the given card number.
     */
    fun isValidCvv(cardNumber: String, cvv: String): Boolean =
        isCvvValid(cardNumber = cardNumber, cvv = cvv)
    /**
     * Validates the card number based on both Luhn algorithm and card brand-specific length rules.
     * Returns true if the card number passes both checks.
     */
    fun isValidCardNumber(cardNumber: String):Boolean= isCardNumberValid(cardNumber=cardNumber)
}