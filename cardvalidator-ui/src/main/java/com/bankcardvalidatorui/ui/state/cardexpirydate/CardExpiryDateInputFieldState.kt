package com.bankcardvalidatorui.ui.state.cardexpirydate

import androidx.compose.ui.text.input.TextFieldValue

internal data class CardExpiryDateInputFieldState(
    val isError: Boolean,
    val errorMessage: String?,
    val isInfoMessage: Boolean?,
    val infoMessage: String?,
    val newSelection: TextFieldValue
)