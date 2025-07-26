package com.bankcardvalidator.brandDetectorEngine.model

import com.bankcardvalidator.enums.CardType

internal object CardBrandRules {
    private val cardTypeRules = listOf(
        CardTypeRule(
            type = CardType.VISA,
            pattern = Regex("^4\\d*"),
            validLengths = setOf(13, 16, 19),
            cvvLength = 3
        ),
        CardTypeRule(
            type = CardType.MASTERCARD,
            pattern = Regex("^(5[1-5]|2[2-7][0-9]{2})\\d*"),
            validLengths = setOf(16),
            cvvLength = 3
        ),
        CardTypeRule(
            type = CardType.AMERICAN_EXPRESS,
            pattern = Regex("^3[47]\\d*"),
            validLengths = setOf(15),
            cvvLength = 4
        ),
        CardTypeRule(
            type = CardType.DISCOVER,
            pattern = Regex("^6(?:011|5|4[4-9])\\d*"),
            validLengths = setOf(16),
            cvvLength = 3
        ),
        CardTypeRule(
            type = CardType.JCB,
            pattern = Regex("^35(2[89]|[3-8][0-9])\\d*"),
            validLengths = setOf(16),
            cvvLength = 3
        ),
        CardTypeRule(
            type = CardType.DINERS_CLUB,
            pattern = Regex("^3(?:0[0-5]|[68])\\d*"),
            validLengths = setOf(14),
            cvvLength = 3
        ),
        CardTypeRule(
            type = CardType.UNION_PAY,
            pattern = Regex("^62\\d*"),
            validLengths = setOf(16, 17, 18, 19),
            cvvLength = 3
        )
    )

    internal fun detect(cardNumber: String): CardType? =
        getRule(cardNumber = cardNumber)?.type


    internal fun getRule(cardNumber: String): CardTypeRule? {
        val clean = cardNumber.filter { it.isDigit() }
        return cardTypeRules.firstOrNull {
            it.validLengths.contains(clean.length) && it.pattern.matches(clean)
        }
    }

    internal fun getCvvLength(cardNumber: String): Int? =
        getRule(cardNumber)?.cvvLength

}