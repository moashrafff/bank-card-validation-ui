package com.bankcardvalidatorui.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.updateWith
import org.junit.Assert.assertEquals
import org.junit.Test

class InputFieldValueTest {

    @Test
    fun `given plain input when text is updated then only text is updated`() {
        val original = InputFieldValue.Plain("Hello")
        val newValue = TextFieldValue(text = "Updated", selection = TextRange(0, 3))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.Plain("Updated"), updated)
    }
    @Test
    fun `given plain input when same text is updated then only text remains unchanged`() {
        val original = InputFieldValue.Plain("Hello")
        val newValue = TextFieldValue(text = "Hello", selection = TextRange(0, 5))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.Plain("Hello"), updated)
    }


    @Test
    fun `given withSelection input when text is updated then full TextFieldValue is updated`() {
        val original = InputFieldValue.WithSelection(TextFieldValue("Old", TextRange(0)))
        val newValue = TextFieldValue(text = "NewText", selection = TextRange(2, 4))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.WithSelection(newValue), updated)
    }
    @Test
    fun `given withSelection input when selection changes then full TextFieldValue is updated`() {
        val original = InputFieldValue.WithSelection(TextFieldValue("Hello", TextRange(0, 5)))
        val newValue = TextFieldValue("Hello", TextRange(1, 3))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.WithSelection(newValue), updated)
    }
    @Test
    fun `given plain input when updated with empty text then only text is cleared`() {
        val original = InputFieldValue.Plain("Hello")
        val newValue = TextFieldValue("", TextRange(0))
        val updated = original.updateWith(newValue)

        assertEquals(InputFieldValue.Plain(""), updated)
    }

}
