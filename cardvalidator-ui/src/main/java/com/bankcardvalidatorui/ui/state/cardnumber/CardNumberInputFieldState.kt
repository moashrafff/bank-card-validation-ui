package com.bankcardvalidatorui.ui.state.cardnumber

import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.enums.CardType

internal data class CardNumberInputFieldState(
    val formattedCardNumber: String,
    val rawDigits: String,
    val newSelection: TextFieldValue,
    val cardBrand: CardType?,
    val isError: Boolean,
    val errorMessage: String?,
    val maxLength: Int,
    val isValidCardNumber: Boolean
)
