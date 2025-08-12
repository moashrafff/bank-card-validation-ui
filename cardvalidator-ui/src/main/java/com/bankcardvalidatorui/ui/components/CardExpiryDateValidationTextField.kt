package com.bankcardvalidatorui.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.R
import com.bankcardvalidatorui.ui.common.ReusableInputField
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.InputFieldValueWithSelectionSaver
import com.bankcardvalidatorui.ui.inputUtils.applySmartExpiryEditing
import com.bankcardvalidatorui.ui.state.cardexpirydate.rememberCardExpiryDateState

@Composable
fun CardExpiryDateTextField(
    invalidFormatErrorMessage: String = stringResource(R.string.expiry_must_be_digits_only),
    invalidMonthErrorMessage: String = stringResource(R.string.invalid_expiry_date_month),
    expiredCardErrorMessage: String = stringResource(R.string.expired_card),
    textFieldLabel: String = stringResource(R.string.expiry_date),
    clearIcon: ImageVector? = null,
    errorMessageFontSize: Float = 12f,
    onExpiryDateChange: (String) -> Unit,
    onExpiryDateValidChange: (Boolean) -> Unit
) {
    var input by rememberSaveable(stateSaver = InputFieldValueWithSelectionSaver, init = {
        mutableStateOf(InputFieldValue.WithSelection(TextFieldValue("")))
    })

    val inputState = rememberCardExpiryDateState(
        expiryInput = input,
        invalidFormatErrorMessage = invalidFormatErrorMessage,
        invalidMonthErrorMessage = invalidMonthErrorMessage,
        expiredCardErrorMessage = expiredCardErrorMessage

    )

    LaunchedEffect(inputState.isError) {
        onExpiryDateValidChange(!inputState.isError)
    }

    ReusableInputField(
        label = textFieldLabel,
        value = input,
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection

            val edited = applySmartExpiryEditing(
                previous = input.value,
                incoming = newInput.value
            )

            input = InputFieldValue.WithSelection(edited)

            onExpiryDateChange(edited.text.filter(Char::isDigit))
        },
        isError = inputState.isError,
        errorMessage = inputState.errorMessage,
        onClearCardNumberClick = {
            input = InputFieldValue.WithSelection(TextFieldValue(""))
            onExpiryDateChange("")
        },
        clearIcon = clearIcon,
        errorMessageFontSize = errorMessageFontSize,
    )
}