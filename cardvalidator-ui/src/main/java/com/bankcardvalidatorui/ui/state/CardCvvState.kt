package com.bankcardvalidatorui.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.api.CardBrandDetector.detectCardBrand
import com.bankcardvalidator.api.CardValidator
import com.bankcardvalidator.brandDetectorEngine.model.CardTypeRule
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue

@Composable
internal fun rememberCardCvvState(
    cvvInput: InputFieldValue.WithSelection,
    optionalCardNumber: String? = null,
    invalidFormatErrorMessage: String,
    invalidCardCvvLengthErrorMessage: String,
): CardCvvInputFieldState {

    val cardNumberDigitsOnly = optionalCardNumber?.filter { it.isDigit() }
    val cardRule: CardTypeRule? = remember(cardNumberDigitsOnly) {
        detectCardBrand(cardNumberDigitsOnly.orEmpty())
    }
    val maxLen = cardRule?.cvvLength ?: 3

    val sanitized = buildString {
        for (ch in cvvInput.value.text) {
            if (ch.isDigit() && length < maxLen) append(ch)
        }
    }

    val validation: CvvValidationResult = remember(sanitized, cardNumberDigitsOnly) {
        if (sanitized.isEmpty()) CvvValidationResult.Valid
        else CardValidator.isCvvValid(cvv = sanitized, cardNumber = cardNumberDigitsOnly)
    }

    val errorMsg = when (validation) {
        CvvValidationResult.InvalidFormat -> invalidFormatErrorMessage
        CvvValidationResult.InvalidLength -> invalidCardCvvLengthErrorMessage
        CvvValidationResult.Valid -> null
    }
    val isError = sanitized.isNotEmpty() && validation != CvvValidationResult.Valid

    return CardCvvInputFieldState(
        cardNumber = optionalCardNumber,
        isError = isError,
        errorMessage = errorMsg,
        maxLength = maxLen,
        newSelection = TextFieldValue(
            text = sanitized,
        )
    )
}