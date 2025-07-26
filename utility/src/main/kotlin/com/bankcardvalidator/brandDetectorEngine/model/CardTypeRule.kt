package com.bankcardvalidator.brandDetectorEngine.model

import com.bankcardvalidator.enums.CardType

internal data class CardTypeRule(
    val type: CardType,
    val pattern: Regex,
    val validLengths: Set<Int>,
    val cvvLength: Int
)