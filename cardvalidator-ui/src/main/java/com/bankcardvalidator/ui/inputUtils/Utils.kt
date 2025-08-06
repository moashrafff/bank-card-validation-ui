package com.bankcardvalidator.ui.inputUtils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.ui.inputTypes.InputFieldValue

fun InputFieldValue.toTextFieldValue(): TextFieldValue = when (this) {
    is InputFieldValue.Plain -> TextFieldValue(this.text)
    is InputFieldValue.WithSelection -> this.value
}

fun InputFieldValue.updateWith(newValue: TextFieldValue) = when (this) {
    is InputFieldValue.Plain -> InputFieldValue.Plain(newValue.text)
    is InputFieldValue.WithSelection -> InputFieldValue.WithSelection(newValue)
}


internal fun calculateNewSelection(
    oldValue: TextFieldValue,
    rawDigits: String,
    formatted: String
): TextRange {
    val digitsBeforeCursor = oldValue.text
        .take(oldValue.selection.start)
        .count { it.isDigit() }

    var count = 0
    var index = 0
    while (count < digitsBeforeCursor && index < formatted.length) {
        if (formatted[index].isDigit()) count++
        index++
    }

    return TextRange(index)
}