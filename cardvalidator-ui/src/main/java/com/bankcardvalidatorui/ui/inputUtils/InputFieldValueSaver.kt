package com.bankcardvalidatorui.ui.inputUtils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue

val InputFieldValueWithSelectionSaver: Saver<InputFieldValue.WithSelection, *> = listSaver(
    save = {
        listOf(
            it.value.text,
            it.value.selection.start,
            it.value.selection.end
        )
    },
    restore = {
        val text = it[0] as String
        val start = it[1] as Int
        val end = it[2] as Int
        InputFieldValue.WithSelection(TextFieldValue(text, TextRange(start, end)))
    }
)