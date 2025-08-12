package com.bankcardvalidatorui.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.applySmartExpiryEditing
import com.bankcardvalidatorui.ui.inputUtils.formatExpiryDisplay
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

    @Test
    fun `Given Invalid Date With 5 Digits when calling formatExpiryDisplay then return First Four Digits Formated` () {
        val input = "12345"
        assertEquals("12/34", input.formatExpiryDisplay())
    }

    @Test
    fun `Given Empty when calling formatExpiryDisplay then return Empty Result` () {
        val input = ""
        assertEquals("", input.formatExpiryDisplay())
    }

    @Test
    fun `Given Valid Date when calling formatExpiryDisplay then return Formatted Result` () {
        val input = "1225"
        assertEquals("12/25", input.formatExpiryDisplay())
    }

    @Test
    fun `Given zero in previous and from 1 to 9 in incoming when calling applySmartExpiryEditing then return MM Month` () {
        val previous = TextFieldValue("0", TextRange(1))
        val incoming = TextFieldValue("01", TextRange(2))
        val result = applySmartExpiryEditing(previous, incoming)
        assertEquals(TextFieldValue("01", TextRange(2)), result)
    }

    @Test
    fun `Given MM month when calling applySmartExpiryEditing then return slashed MM Month` () {
        val previous = TextFieldValue("01", TextRange(2))
        val incoming = TextFieldValue("013", TextRange(3))
        val result = applySmartExpiryEditing(previous, incoming)
        assertEquals(TextFieldValue("01/3", TextRange(4)), result)
    }
    @Test
    fun `Given month from 2 to 9 month when calling applySmartExpiryEditing then return MM Month` () {
        val previous = TextFieldValue("", TextRange(1))
        val incoming = TextFieldValue("9", TextRange(1))
        val result = applySmartExpiryEditing(previous, incoming)
        assertEquals(TextFieldValue("09", TextRange(2)), result)
    }

}
