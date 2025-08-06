package com.bankcardvalidator.api

import com.bankcardvalidator.brandDetectorEngine.model.CardBrandRules
import com.bankcardvalidator.brandDetectorEngine.model.CardTypeRule
import com.bankcardvalidator.enums.CardType

object CardBrandDetector {

    /**
     * Detects the card type (Visa, Mastercard, etc.) based on card number.
     * Returns null if the type is unknown.
     */
    fun detectCardType(cardNumber: String): CardType? {
        return CardBrandRules.detect(cardNumber)
    }

    /**
     * Returns the required CVV length for the card type, if known.
     */
    fun getRequiredCvvLength(cardNumber: String): Int? {
        return CardBrandRules.getCvvLength(cardNumber)
    }

    /**
     * Returns true if the card number matches a known card brand pattern.
     */
    fun isSupportedCard(cardNumber: String): Boolean {
        if (!cardNumber.all { it.isDigit() || it.isWhitespace() }) return false
        return CardBrandRules.getRule(cardNumber) != null
    }

    /**
     * Returns the card brand rule for the card number, if known.
     */
    fun getCardBrandRule(cardNumber: String): CardTypeRule? {
        return CardBrandRules.getRule(cardNumber)
    }
}