package com.bankcardvalidatorui.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.delay

internal class PasswordVisualTransformation private constructor(
    private val showLastVisible: Boolean,
    private val mask: Char = '•'
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val length = text.text.length
        if (length == 0) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        val masked = buildString {
            if (length > 1) {
                append(mask.toString().repeat(length - 1))
            }
            append(
                if (showLastVisible) text.text.last() else mask
            )
        }

        return TransformedText(AnnotatedString(masked), OffsetMapping.Identity)
    }

    companion object {
        @Composable
        fun remember(maskDelayMillis: Long = 1500L, mask: Char = '•', text: String = ""): VisualTransformation {
            var showLast by remember { mutableStateOf(true) }

            LaunchedEffect(text) {
                if (text.isNotEmpty()) {
                    showLast = true
                    delay(maskDelayMillis)
                    showLast = false
                }
            }

            return remember(showLast, text) {
               PasswordVisualTransformation(showLast, mask)
            }
        }
    }
}