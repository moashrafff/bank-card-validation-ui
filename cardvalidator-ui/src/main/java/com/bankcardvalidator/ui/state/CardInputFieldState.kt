package com.bankcardvalidator.ui.state

import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.enums.CardType

internal data class CardInputFieldState(
    val formattedCardNumber: String,
    val rawDigits: String,
    val newSelection: TextFieldValue,
    val cardBrand: CardType?,
    val isError: Boolean,
    val errorMessage: String?,
    val maxLength: Int
)
