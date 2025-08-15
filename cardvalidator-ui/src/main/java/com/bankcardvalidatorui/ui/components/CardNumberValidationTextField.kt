package com.bankcardvalidatorui.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.R
import com.bankcardvalidatorui.ui.common.CardBrandIcon
import com.bankcardvalidatorui.ui.common.ReusableInputField
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.InputFieldValueWithSelectionSaver
import com.bankcardvalidatorui.ui.state.cardnumber.rememberCardNumberInputState

@Composable
fun CardNumberTextField(
    modifier:Modifier = Modifier,
    invalidFormatErrorMessage: String = stringResource(R.string.card_number_must_be_digits_only),
    invalidCardNumberErrorMessage: String = stringResource(R.string.invalid_card_number),
    unknownCardBrandErrorMessage: String = stringResource(R.string.incomplete_card_number),
    textFieldLabel: String = stringResource(R.string.card_number),
    completeFocusDirection: FocusDirection? = null,
    clearIcon: ImageVector? = null,
    errorMessageFontSize: Float = 12f,
    onCardNumberChange: (String) -> Unit,
    onCardNumberValidChange: ((Boolean) -> Unit)? = null
) {
    var input by rememberSaveable(stateSaver = InputFieldValueWithSelectionSaver, init = {
        mutableStateOf(
            InputFieldValue.WithSelection(TextFieldValue(""))
        )
    })

    val focusManager = LocalFocusManager.current
    val inputState = rememberCardNumberInputState(
        input = input,
        invalidFormatErrorMessage = invalidFormatErrorMessage,
        invalidCardNumberErrorMessage = invalidCardNumberErrorMessage,
        unknownCardBrandErrorMessage = unknownCardBrandErrorMessage
    )
    var wasValid by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(inputState.isValidCardNumber) {

        onCardNumberValidChange?.let { it(inputState.isValidCardNumber) }

        if (!wasValid && inputState.isValidCardNumber) {
            if (completeFocusDirection != null) focusManager.moveFocus(
                completeFocusDirection
            )
            else {
                focusManager.clearFocus()
            }
        }
        wasValid = inputState.isValidCardNumber
    }
    ReusableInputField(
        modifier = modifier,
        label = textFieldLabel,
        value = InputFieldValue.WithSelection(inputState.newSelection),
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection
            val digitsOnly = newInput.value.text.filter { ch -> ch.isDigit() }
            if (digitsOnly.length <= inputState.maxLength) {
                input = newInput
                onCardNumberChange(digitsOnly)
            }
        },
        isError = inputState.isError,
        errorMessage = inputState.errorMessage,
        cardBrandIcon = { CardBrandIcon(inputState.cardBrand) },
        onClearCardNumberClick = {
            input = InputFieldValue.WithSelection(TextFieldValue(""))
            onCardNumberChange("")
        },
        clearIcon = clearIcon,
        errorMessageFontSize = errorMessageFontSize,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

    )
}
