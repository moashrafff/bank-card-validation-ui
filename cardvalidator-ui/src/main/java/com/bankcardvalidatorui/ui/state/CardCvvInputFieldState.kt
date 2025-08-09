package com.bankcardvalidatorui.ui.state

internal data class CardCvvInputFieldState(
    val cardNumber: String?,
    val isError: Boolean,
    val errorMessage: String?,
    val maxLength: Int
)
