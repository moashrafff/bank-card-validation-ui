package com.bankcardvalidator.cardNumberValidatorTest

import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_LENGTH_WITH_VALID_LUHN
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_SHORT
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_TOO_LONG
import com.bankcardvalidator.testUtils.CardTestConstants.LETTERS
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_AMEX
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_CARD_NUMBER_WITH_DASHES_AND_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DINERS
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DISCOVER
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_JCB
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_LENGTH_MASTERCARD_WITH_INVALID_LUHN
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_MASTERCARD
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_UNIONPAY
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_VISA
import com.bankcardvalidator.typoValidationEngine.isCardNumberValid
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CardNumberValidatorTest {
    @Test
    fun `Given a valid length with valid luhn Mastercard when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_MASTERCARD))
    }
    @Test
    fun `Given a valid length with valid luhn Visa when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_VISA))
    }
    @Test
    fun `Given a valid length with valid luhn Jcb when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_JCB))
    }
    @Test
    fun `Given a valid length with valid luhn Diners when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_DINERS))
    }
    @Test
    fun `Given a valid length with valid luhn UnionPay when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_UNIONPAY))
    }
    @Test
    fun `Given a valid length with valid luhn Discover when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_DISCOVER))
    }
    @Test
    fun `Given a valid length with valid luhn Amex when calling isCardNumberValid then return true`(){
        assertTrue(isCardNumberValid(VALID_AMEX))
    }
    @Test
    fun `Given an invalid length with invalid luhn when calling isCardNumberValid then return false`(){
        assertFalse(isCardNumberValid(INVALID_TOO_LONG))
    }
    @Test
    fun `Given an invalid length with valid luhn when calling isCardNumberValid then return false`(){
        assertFalse(isCardNumberValid(INVALID_LENGTH_WITH_VALID_LUHN))
    }
    @Test
    fun `Given a valid length Mastercard with invalid luhn when calling isCardNumberValid then return false`(){
        assertFalse(isCardNumberValid(VALID_LENGTH_MASTERCARD_WITH_INVALID_LUHN))
    }
    @Test
    fun `Given a valid card number with dashes and spaces when calling isCardNumberValid then return true`() {
        assertTrue(isCardNumberValid(VALID_CARD_NUMBER_WITH_DASHES_AND_SPACES))
    }
    @Test
    fun `Given an empty string when calling isCardNumberValid then return false`() {
        assertFalse(isCardNumberValid(""))
    }
    @Test
    fun `Given a string with only spaces when calling isCardNumberValid then return false`() {
        assertFalse(isCardNumberValid("      "))
    }

    @Test
    fun `Given a string with only letters when calling isCardNumberValid then return false`() {
        assertFalse(isCardNumberValid(LETTERS))
    }
    @Test
    fun `Given too short number when calling isCardNumberValid then return false`() {
        assertFalse(isCardNumberValid(INVALID_SHORT))
    }

}