package com.bankcardvalidatorui.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.R
import com.bankcardvalidatorui.ui.common.ReusableInputField
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.InputFieldValueWithSelectionSaver
import com.bankcardvalidatorui.ui.state.rememberCardCvvState

@Composable
fun CardCvvTextField(
    invalidFormatErrorMessage: String = stringResource(R.string.cvv_must_be_digits_only),
    invalidCvvLengthErrorMessage: String = stringResource(R.string.invalid_cvv_length),
    textFieldLabel: String = stringResource(R.string.cvv),
    optionalCardNumber: String? = null,
    clearIcon: ImageVector? = null,
    errorMessageFontSize: Float = 12f,
    onCvvChange: (String) -> Unit,
    onCvvValidChange: (Boolean) -> Unit
) {
    var input by rememberSaveable(stateSaver = InputFieldValueWithSelectionSaver) {
        mutableStateOf(InputFieldValue.WithSelection(TextFieldValue("")))
    }

    val inputState = rememberCardCvvState(
        cvvInput = input,
        optionalCardNumber = optionalCardNumber,
        invalidFormatErrorMessage = invalidFormatErrorMessage,
        invalidCardCvvLengthErrorMessage = invalidCvvLengthErrorMessage
    )

    // send VALID (not error)
    LaunchedEffect(inputState.isError) {
        onCvvValidChange(!inputState.isError)
    }

    // Trim current text if max length shrinks (e.g., Amex->Visa)
    LaunchedEffect(inputState.maxLength) {
        val digits = input.value.text.filter(Char::isDigit)
        if (digits.length > inputState.maxLength) {
            val trimmed = digits.take(inputState.maxLength)
            input = InputFieldValue.WithSelection(TextFieldValue(trimmed))
            onCvvChange(trimmed)
        }
    }

    ReusableInputField(
        label = textFieldLabel,
        value = InputFieldValue.WithSelection(input.value),
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection
            val digitsOnly = newInput.value.text.filter(Char::isDigit).take(inputState.maxLength)

            // write back the sanitized value so letters/paste are blocked visually
            input = InputFieldValue.WithSelection(
                TextFieldValue(digitsOnly) // cursor at end is fine for CVV
            )
            onCvvChange(digitsOnly)
        },
        isError = inputState.isError,
        errorMessage = inputState.errorMessage,
        onClearCardNumberClick = {
            input = InputFieldValue.WithSelection(TextFieldValue(""))
            onCvvChange("")
        },
        clearIcon = clearIcon,
        errorMessageFontSize = errorMessageFontSize,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation()
    )
}