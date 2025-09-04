package com.bankcardvalidatorui.ui.inputTypes

import androidx.compose.ui.text.input.TextFieldValue

internal sealed class InputFieldValue {
    data class Plain(val text: String) : InputFieldValue()
    data class WithSelection(val value: TextFieldValue) : InputFieldValue()
}