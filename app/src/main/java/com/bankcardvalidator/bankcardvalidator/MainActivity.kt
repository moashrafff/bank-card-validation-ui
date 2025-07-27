package com.bankcardvalidator.bankcardvalidator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect

import com.bankcardvalidator.api.CardBrandDetector
import com.bankcardvalidator.api.CardTypoDetector
import com.bankcardvalidator.api.ExpiryDateValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                val cardNumber = "123"
                CardTypoDetector.isValidCard(cardNumber = cardNumber)
                CardBrandDetector.detectCardType(cardNumber = cardNumber)
                ExpiryDateValidator.validate("12/25")
            }
        }
    }
}

