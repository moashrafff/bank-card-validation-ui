package com.bankcardvalidator.brandDetectorEngine.model

import com.bankcardvalidator.enums.CardType

 data class CardTypeRule(
    val type: CardType,
    val pattern: Regex,
    val validLengths: Set<Int>,
    val cvvLength: Int
)