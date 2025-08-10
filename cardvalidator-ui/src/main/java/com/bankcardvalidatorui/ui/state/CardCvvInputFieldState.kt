package com.bankcardvalidatorui.ui.state

import androidx.compose.ui.text.input.TextFieldValue

internal data class CardCvvInputFieldState(
    val cardNumber: String?,
    val isError: Boolean,
    val errorMessage: String?,
    val maxLength: Int,
    val newSelection: TextFieldValue
)
