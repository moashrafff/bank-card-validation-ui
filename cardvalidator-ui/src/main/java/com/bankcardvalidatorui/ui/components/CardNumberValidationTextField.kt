package com.bankcardvalidatorui.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.ui.common.CardBrandIcon
import com.bankcardvalidatorui.ui.common.ReusableInputField
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.InputFieldValueWithSelectionSaver
import com.bankcardvalidatorui.ui.state.rememberCardNameState

@Composable
fun CardNumberTextField(
    keyboardOptions: KeyboardOptions,
    invalidFormatErrorMessage: String = "Card number must be digits only",
    invalidCardNumberErrorMessage: String = "Invalid card number",
    unknownCardBrandErrorMessage: String = "Unknown card brand",
    textFieldLabel: String = "Card Number"
) {
    var input by rememberSaveable(stateSaver = InputFieldValueWithSelectionSaver, init = {
        mutableStateOf(
            InputFieldValue.WithSelection(TextFieldValue(""))
        )
    })
    val inputState = rememberCardNameState(
        input = input,
        invalidFormatErrorMessage = invalidFormatErrorMessage,
        invalidCardNumberErrorMessage = invalidCardNumberErrorMessage,
        unknownCardBrandErrorMessage = unknownCardBrandErrorMessage
    )

    ReusableInputField(
        label = textFieldLabel,
        value = InputFieldValue.WithSelection(inputState.newSelection),
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection
            val digitsOnly = newInput.value.text.filter { ch -> ch.isDigit() }
            if (digitsOnly.length <= inputState.maxLength) {
                input = newInput
            }
        },
        isError = inputState.isError,
        errorMessage = inputState.errorMessage,
        keyboardOptions = keyboardOptions,
        cardBrandIcon =
            { CardBrandIcon(inputState.cardBrand) }
    )
}
