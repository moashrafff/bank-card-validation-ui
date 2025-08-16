package com.bankcardvalidatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.unit.dp
import com.bankcardvalidator.api.CardBrandDetector
import com.bankcardvalidator.api.CardValidator
import com.bankcardvalidatorui.ui.components.CardCvvTextField
import com.bankcardvalidatorui.ui.components.CardExpiryDateTextField
import com.bankcardvalidatorui.ui.components.CardNumberTextField


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                val cardNumber = "4539148803436467"

                CardValidator.isCardNumberValid(cardNumber = cardNumber)
                CardValidator.isExpiryDateValid(expiryDate = "12/25")
                CardValidator.isCvvValid(cardNumber = cardNumber, cvv = "123")

                CardBrandDetector.detectCardType(cardNumber = cardNumber)
                CardBrandDetector.isSupportedCard(cardNumber = cardNumber)
                CardBrandDetector.getRequiredCvvLength(cardNumber = cardNumber)
            }

            var cardNumberEntered by remember { mutableStateOf("") }

            Column(modifier = Modifier.padding(top = 19.dp)) {
                CardNumberTextField(
                    modifier = Modifier.fillMaxWidth(),
                    onCardNumberChange = { updatedCardNumber ->
                        cardNumberEntered = updatedCardNumber
                    },
                    completeFocusDirection = FocusDirection.Down
                )
                Row {
                    Box(modifier = Modifier.weight(1f)) {
                        CardExpiryDateTextField(
                            completeFocusDirection = FocusDirection.Right,
                            onExpiryDateChange = {},
                            onExpiryDateValidChange = {}
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        CardCvvTextField(
                            canCloseKeyBoardAfterValidation = true,
                            optionalCardNumber = cardNumberEntered,
                            onCvvChange = {},
                            onCvvValidChange = {}
                        )
                    }
                }
            }
        }
    }
}

