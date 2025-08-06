package com.bankcardvalidator.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.api.CardBrandDetector
import com.bankcardvalidator.api.CardValidator.isCardNumberValid
import com.bankcardvalidator.result.CardNumberValidationResult
import com.bankcardvalidator.ui.common.CardBrandIcon
import com.bankcardvalidator.ui.common.ReusableInputField
import com.bankcardvalidator.ui.inputTypes.InputFieldValue
import com.bankcardvalidator.ui.inputUtils.calculateNewSelection

@Composable
fun CardNumberTextField(
    keyboardOptions: KeyboardOptions,
    invalidFormatErrorMessage: String = "Card number must be digits only",
    invalidCardNumberErrorMessage: String = "Invalid card number",
    unknownCardBrandErrorMessage: String = "Unknown card brand",
    textFieldLabel: String = "Card Number"
) {
    var input by remember { mutableStateOf(InputFieldValue.WithSelection(TextFieldValue(""))) }

    val cardNumberDigitsOnly = input.value.text.filter { it.isDigit() }

    val cardRule = remember(cardNumberDigitsOnly) {
        CardBrandDetector.getCardBrandRule(cardNumberDigitsOnly)
    }
    val cardBrand = cardRule?.type
    val maxLength = cardRule?.validLengths?.maxOrNull() ?: 19

    val limitedCardNumberDigits = cardNumberDigitsOnly.take(maxLength)
    val formattedCardNumber = limitedCardNumberDigits.chunked(4).joinToString(" ")

    val newSelection = calculateNewSelection(
        oldValue = input.value,
        rawDigits = limitedCardNumberDigits,
        formatted = formattedCardNumber
    )

    val validationResult =
        if (limitedCardNumberDigits.isEmpty()) null else isCardNumberValid(limitedCardNumberDigits)
    val isError = validationResult != null && validationResult != CardNumberValidationResult.Valid

    val errorMessage = when (validationResult) {
        CardNumberValidationResult.InvalidFormat -> invalidFormatErrorMessage
        CardNumberValidationResult.InvalidLuhn -> invalidCardNumberErrorMessage
        CardNumberValidationResult.UnknownCardBrand -> unknownCardBrandErrorMessage
        CardNumberValidationResult.Valid -> null
        null -> null
    }

    ReusableInputField(
        label = textFieldLabel,
        value = InputFieldValue.WithSelection(TextFieldValue(formattedCardNumber, newSelection)),
        onValueChange = {
            val newInput = it as InputFieldValue.WithSelection
            val digitsOnly = newInput.value.text.filter { ch -> ch.isDigit() }
            if (digitsOnly.length <= maxLength) {
                input = newInput
            }
        },
        isError = isError,
        errorMessage = errorMessage,
        keyboardOptions = keyboardOptions,
        cardBrandIcon = {
            key(cardBrand) {
                CardBrandIcon(cardBrand)
            }
        }
    )

}


