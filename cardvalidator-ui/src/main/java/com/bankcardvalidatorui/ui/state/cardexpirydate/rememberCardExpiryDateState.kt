package com.bankcardvalidatorui.ui.state.cardexpirydate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.api.CardValidator
import com.bankcardvalidator.result.ExpiryValidationResult
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.formatExpiryDisplay
import java.util.Calendar

@Composable
internal fun rememberCardExpiryDateState(
    expiryInput: InputFieldValue.WithSelection,
    invalidFormatErrorMessage: String,
    invalidMonthErrorMessage: String,
    expiredCardErrorMessage: String,
    tooFarMessage: String
): CardExpiryDateInputFieldState {

    val digitsOnly = remember(expiryInput.value.text) {
        buildString(capacity = 4) {
            for (ch in expiryInput.value.text) {
                if (ch.isDigit() && length < 4) append(ch)
            }
        }
    }

    val display = remember(digitsOnly) {
        digitsOnly.formatExpiryDisplay()
    }

    val validation: ExpiryValidationResult? = remember(digitsOnly) {
        if (digitsOnly.isEmpty()) null
        else CardValidator.isExpiryDateValid(digitsOnly)
    }
    var isInfoMessage = false

    val errorMsg = when (validation) {
        ExpiryValidationResult.InvalidFormat -> invalidFormatErrorMessage
        ExpiryValidationResult.InvalidMonth -> invalidMonthErrorMessage
        ExpiryValidationResult.Expired -> expiredCardErrorMessage
        ExpiryValidationResult.Valid, null -> null
    }
    if (digitsOnly.length == 4) {
        val year = digitsOnly.takeLast(2).toInt()

        val calendar = Calendar.getInstance()
        val fullCurrentYear = calendar.get(Calendar.YEAR)
        val fullExpiryYear = 2000 + year

        val maxAllowedYear = fullCurrentYear + 20
        if (fullExpiryYear > maxAllowedYear) {
            isInfoMessage = true
        }
    }

    val isError =
        validation != null && validation != ExpiryValidationResult.Valid

    return CardExpiryDateInputFieldState(
        isError = isError,
        errorMessage = errorMsg,
        isInfoMessage = isInfoMessage,
        infoMessage = if (isInfoMessage) tooFarMessage else null,
        newSelection = TextFieldValue(text = display)
    )
}