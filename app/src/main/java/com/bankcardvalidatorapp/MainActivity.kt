package com.bankcardvalidatorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

import com.bankcardvalidator.api.CardBrandDetector
import com.bankcardvalidator.api.CardValidator

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
            var isCardNumberValid by remember { mutableStateOf(false) }

            CardNumberTextField(
                KeyboardOptions(keyboardType = KeyboardType.Number),
                onCardNumberChange = { updatedCardNumber ->
                    cardNumberEntered = updatedCardNumber
                },
                onCardNumberValidChange = { valid -> isCardNumberValid = valid }
            )
            Log.d("cardNum", cardNumberEntered)
            Log.d("valid", isCardNumberValid.toString())
        }
    }
}

