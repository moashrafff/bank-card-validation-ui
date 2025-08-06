package com.bankcardvalidator.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidator.ui.inputTypes.InputFieldValue
import com.bankcardvalidator.ui.inputUtils.updateWith
import org.junit.Assert.assertEquals
import org.junit.Test

class InputFieldValueTest {

    @Test
    fun `Plain should update only text`() {
        val original = InputFieldValue.Plain("Hello")
        val newValue = TextFieldValue(text = "Updated", selection = TextRange(0, 3))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.Plain("Updated"), updated)
    }

    @Test
    fun `WithSelection should update full TextFieldValue`() {
        val original = InputFieldValue.WithSelection(TextFieldValue("Old", TextRange(0)))
        val newValue = TextFieldValue(text = "NewText", selection = TextRange(2, 4))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.WithSelection(newValue), updated)
    }
}
