package com.bankcardvalidator.api

import com.bankcardvalidator.typoValidationEngine.isLuhnValid

object CardTypoDetector {
    /**
     * Detects the card typo, structure.  based on card number and prevent input errors.
     * Returns false if the card number is valid.
     */
    fun isValidCard(cardNumber: String) : Boolean = isLuhnValid(cardNumber = cardNumber)
}