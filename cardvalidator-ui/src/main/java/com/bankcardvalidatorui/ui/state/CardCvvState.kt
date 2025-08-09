package com.bankcardvalidatorui.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.api.CardBrandDetector.detectCardBrand
import com.bankcardvalidator.api.CardValidator
import com.bankcardvalidator.api.CardValidator.isCardNumberValid
import com.bankcardvalidator.brandDetectorEngine.model.CardTypeRule
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult
import com.bankcardvalidator.result.CardNumberValidationResult
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.calculateNewSelection

@Composable
internal fun rememberCardCvvState(
    cvvInput: InputFieldValue.WithSelection,
    optionalCardNumber : String? = null,
    invalidFormatErrorMessage: String,
    invalidCardCvvLengthErrorMessage: String,
    validCvvMessage: String? = "valid cvv"
) : CardInputFieldState {

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
        CvvValidationResult.Valid -> validCvvMessage
    }

    return CardInputFieldState(
        formattedCardNumber = formattedCardNumber,
        rawDigits = limitedCardNumberDigits,
        newSelection = newTextFieldValue,
        cardBrand = null,
        isError = isError,
        errorMessage = cvvValidationMessage,
        maxLength = cardCvvMaxLength ?: 3
    )
    
}