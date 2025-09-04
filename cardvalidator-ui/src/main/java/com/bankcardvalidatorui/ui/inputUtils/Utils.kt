package com.bankcardvalidatorui.ui.inputUtils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue

internal fun InputFieldValue.toTextFieldValue(): TextFieldValue = when (this) {
    is InputFieldValue.Plain -> TextFieldValue(this.text)
    is InputFieldValue.WithSelection -> this.value
}

internal fun InputFieldValue.updateWith(newValue: TextFieldValue) = when (this) {
    is InputFieldValue.Plain -> InputFieldValue.Plain(newValue.text)
    is InputFieldValue.WithSelection -> InputFieldValue.WithSelection(newValue)
}


internal fun calculateNewSelection(
    oldValue: TextFieldValue,
    formatted: String
): TextRange {
    val digitsBeforeCursor = oldValue.text
        .take(oldValue.selection.start)
        .count { it.isDigit() }

    var count = 0
    var index = 0
    while (count < digitsBeforeCursor && index < formatted.length) {
        if (formatted[index].isDigit()) count++
        index++
    }

    return TextRange(index)
}

fun String.formatExpiryDisplay(): String {
    val digitsOnlyDate = this
    val d = digitsOnlyDate.take(4)
    return when {
        d.isEmpty()      -> ""
        d.length <= 2    -> d
        else             -> d.substring(0, 2) + "/" + d.substring(2)
    }
}

/**
 * Smart expiry editor.
 * - Auto-prepends 0 if first digit is 2..9
 * - Inserts slash after MM
 * - Caps to 4 digits total
 * - Puts caret at end (after slash once MM is complete)
 */
internal fun applySmartExpiryEditing(
    previous: TextFieldValue,
    incoming: TextFieldValue
): TextFieldValue {
    val prevDigits = previous.text.filter(Char::isDigit)
    val rawDigits = incoming.text.filter(Char::isDigit)

    val coerced = when {
        prevDigits.isEmpty() && rawDigits.isNotEmpty() && rawDigits[0] in '2'..'9' -> {
            "0$rawDigits"
        }

        prevDigits.length == 1 &&
                prevDigits[0] == '1' &&
                rawDigits.length >= 2 &&
                rawDigits[1] in '3'..'9' -> {
            "0${prevDigits}${rawDigits.drop(1)}"
        }

        else -> rawDigits
    }

    val digits = coerced.take(4)
    val display = digits.formatExpiryDisplay()

    return TextFieldValue(text = display, selection = TextRange(display.length))
}