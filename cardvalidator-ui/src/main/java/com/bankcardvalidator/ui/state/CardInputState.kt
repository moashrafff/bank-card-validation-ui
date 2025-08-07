package com.bankcardvalidator.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.ui.inputTypes.InputFieldValue
import com.bankcardvalidator.ui.inputUtils.calculateNewSelection
import com.bankcardvalidator.brandDetectorEngine.model.CardTypeRule
import com.bankcardvalidator.api.CardBrandDetector.detectCardBrand
import com.bankcardvalidator.api.CardValidator.isCardNumberValid
import com.bankcardvalidator.result.CardNumberValidationResult

@Composable
internal fun rememberCardInputState(
    input: InputFieldValue.WithSelection,
    invalidFormatErrorMessage: String,
    invalidCardNumberErrorMessage: String,
    unknownCardBrandErrorMessage: String
) : CardInputFieldState {
    val cardNumberDigitsOnly = input.value.text.filter { it.isDigit() }

    val cardRule : CardTypeRule? = remember(cardNumberDigitsOnly) {
        detectCardBrand(cardNumberDigitsOnly)
    }
    val cardBrand by remember(cardNumberDigitsOnly) {
        derivedStateOf {
            if (cardNumberDigitsOnly.length >= 4) {
                detectCardBrand(cardNumberDigitsOnly)?.type
            } else {
                null
            }
        }
    }
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
    val newTextFieldValue = TextFieldValue(formattedCardNumber, newSelection)

    return CardInputFieldState(
        formattedCardNumber = formattedCardNumber,
        rawDigits = limitedCardNumberDigits,
        newSelection = newTextFieldValue,
        cardBrand = cardBrand,
        isError = isError,
        errorMessage = errorMessage,
        maxLength = maxLength
    )
}