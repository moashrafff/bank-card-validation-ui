package com.bankcardvalidatorui.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.bankcardvalidator.api.CardBrandDetector.detectCardBrand
import com.bankcardvalidator.api.CardValidator
import com.bankcardvalidator.brandDetectorEngine.model.CardTypeRule
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue

@Composable
internal fun rememberCardCvvState(
    cvvInput: InputFieldValue.WithSelection,
    optionalCardNumber : String? = null,
    invalidFormatErrorMessage: String,
    invalidCardCvvLengthErrorMessage: String,
) : CardCvvInputFieldState {

    val cardCvvDigitsOnly = cvvInput.value.text.filter { it.isDigit() }
    val cardNumberDigitsOnly = optionalCardNumber?.filter { it.isDigit() }

    val isCvvValid : CvvValidationResult = remember(cardCvvDigitsOnly) {
        CardValidator.isCvvValid(cvv = cardCvvDigitsOnly, cardNumber = optionalCardNumber)
    }
    val cardRule : CardTypeRule? = remember(cardNumberDigitsOnly) {
        detectCardBrand(cardNumberDigitsOnly.orEmpty())
    }

    val cardCvvMaxLength = cardRule?.cvvLength

    val cvvValidationMessage = when (isCvvValid) {
        CvvValidationResult.InvalidFormat -> invalidFormatErrorMessage
        CvvValidationResult.InvalidLength -> invalidCardCvvLengthErrorMessage
        CvvValidationResult.Valid -> null
    }

    val isError = isCvvValid != CvvValidationResult.Valid

    return CardCvvInputFieldState(
        cardBrand = null,
        isError = isError,
        errorMessage = cvvValidationMessage,
        maxLength = cardCvvMaxLength ?: 3
    )

}