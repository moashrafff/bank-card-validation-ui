package com.bankcardvalidatorui.ui.state

import com.bankcardvalidator.enums.CardType

internal data class CardCvvInputFieldState(
    val cardBrand: CardType?,
    val isError: Boolean,
    val errorMessage: String?,
    val maxLength: Int
)
