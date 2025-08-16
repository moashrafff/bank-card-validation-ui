package com.bankcardvalidatorui.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.R
import com.bankcardvalidatorui.ui.common.ReusableInputField
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.InputFieldValueWithSelectionSaver
import com.bankcardvalidatorui.ui.state.cardcvv.rememberCardCvvState
import com.bankcardvalidatorui.ui.utils.PasswordVisualTransformation

@Composable
fun CardCvvTextField(
    modifier: Modifier = Modifier,
    invalidFormatErrorMessage: String = stringResource(R.string.cvv_must_be_digits_only),
    invalidCvvLengthErrorMessage: String = stringResource(R.string.invalid_cvv_length),
    textFieldLabel: String = stringResource(R.string.cvv),
    canCloseKeyBoardAfterValidation: Boolean = false,
    optionalCardNumber: String? = null,
    clearIcon: ImageVector? = null,
    errorMessageFontSize: Float = 12f,
    onCvvChange: (String) -> Unit,
    onCvvValidChange: (Boolean) -> Unit
) {
    var input by rememberSaveable(stateSaver = InputFieldValueWithSelectionSaver, init = {
        mutableStateOf(InputFieldValue.WithSelection(TextFieldValue("")))
    })
    val inputState = rememberCardCvvState(
        cvvInput = input,
        optionalCardNumber = optionalCardNumber,
        invalidFormatErrorMessage = invalidFormatErrorMessage,
        invalidCardCvvLengthErrorMessage = invalidCvvLengthErrorMessage
    )

    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(inputState.isError) {
        onCvvValidChange(!inputState.isError)

    }

    ReusableInputField(
        modifier = modifier,
        label = textFieldLabel,
        value = input,
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection
            val maxLen = inputState.maxLength

            val raw = newInput.value
            val digits = raw.text.filter(Char::isDigit).take(maxLen)

            val sel = raw.selection
            val end = minOf(sel.end, digits.length)
            val start = minOf(sel.start, end)

            input = InputFieldValue.WithSelection(
                TextFieldValue(digits, TextRange(start, end))
            )
            onCvvChange(digits)

            if (digits.length == maxLen && canCloseKeyBoardAfterValidation)
                keyboardController?.hide()

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
        visualTransformation =  PasswordVisualTransformation.remember(
            maskDelayMillis = 1500L,
            text = input.value.text
        )

    )
}