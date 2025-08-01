package com.bankcardvalidator.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.api.CardValidator.isCardNumberValid
import com.bankcardvalidator.result.CardNumberValidationResult
import com.bankcardvalidator.ui.common.ReusableInputField
import com.bankcardvalidator.ui.inputTypes.InputFieldValue

@Composable
fun CardNumberTextField() {
    var input by remember { mutableStateOf(InputFieldValue.WithSelection(TextFieldValue(""))) }

    val rawDigits = input.value.text.filter { it.isDigit() }.take(19)

    val formatted = rawDigits.chunked(4).joinToString(" ")

    val newSelection = calculateNewSelection(
        oldValue = input.value,
        rawDigits = rawDigits,
        formatted = formatted
    )

    val validationResult = if (rawDigits.isEmpty()) null else isCardNumberValid(rawDigits)
    val isError = validationResult != null && validationResult != CardNumberValidationResult.Valid

    val errorMessage = when (validationResult) {
        CardNumberValidationResult.InvalidFormat -> "Card number must be digits only"
        CardNumberValidationResult.InvalidLuhn -> "Invalid card number"
        CardNumberValidationResult.UnknownCardBrand -> "Unknown Card Brand"
        else -> null
    }

    ReusableInputField(
        label = "Card Number",
        value = InputFieldValue.WithSelection(TextFieldValue(formatted, newSelection)),
        onValueChange = { input = it as InputFieldValue.WithSelection },
        isError = isError,
        errorMessage = errorMessage
    )

}


fun calculateNewSelection(
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
