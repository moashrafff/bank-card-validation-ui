package com.bankcardvalidator.cardDetectorTests

import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_CARD
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_NON_DIGITS
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_SHORT
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_TOO_LONG
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_AMEX
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_DINERS
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_DISCOVER
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_JCB
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_MASTERCARD
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_UNIONPAY
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_VISA
import com.bankcardvalidator.api.CardBrandDetector
import com.bankcardvalidator.enums.CardType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CardBrandDetectorTest {

    @Test
    fun `Given Visa valid card number when calling detectCardType then return CardType VISA`() {
        assertEquals(CardType.VISA, CardBrandDetector.detectCardType(VALID_VISA))
    }

    @Test
    fun `Given MasterCard valid card number when calling detectCardType then return CardType MASTERCARD`() {
        assertEquals(CardType.MASTERCARD, CardBrandDetector.detectCardType(VALID_MASTERCARD))
    }

    @Test
    fun `Given AMEX valid card number when calling detectCardType then return CardType AMEX`() {
        assertEquals(CardType.AMERICAN_EXPRESS, CardBrandDetector.detectCardType(VALID_AMEX))
    }

    @Test
    fun `Given DISCOVER valid card number when calling detectCardType then return CardType DISCOVER`() {
        assertEquals(CardType.DISCOVER, CardBrandDetector.detectCardType(VALID_DISCOVER))
    }

    @Test
    fun `Given UNIONPAY valid card number when calling detectCardType then return CardType UNIONPAY`() {
        assertEquals(CardType.UNION_PAY, CardBrandDetector.detectCardType(VALID_UNIONPAY))
    }

    @Test
    fun `Given JCB valid card number when calling detectCardType then return CardType JCB`() {
        assertEquals(CardType.JCB, CardBrandDetector.detectCardType(VALID_JCB))
    }

    @Test
    fun `Given DINERS valid card number when calling detectCardType then return CardType DINERS`() {
        assertEquals(CardType.DINERS_CLUB, CardBrandDetector.detectCardType(VALID_DINERS))
    }

    @Test
    fun `Given invalid card number when calling detectCardType then return CardType VISA`() {
        assertEquals(null, CardBrandDetector.detectCardType(INVALID_CARD))
    }

    @Test
    fun `Given VISA valid card number when calling getRequiredCvvLength then return 3`() {
        assertEquals(3, CardBrandDetector.getRequiredCvvLength(VALID_VISA))
    }

    @Test
    fun `Given AMERICAN_EXPRESS valid card number when calling getRequiredCvvLength then return 4`() {
        assertEquals(4, CardBrandDetector.getRequiredCvvLength(VALID_AMEX))
    }

    @Test
    fun `Given invalid card number when calling getRequiredCvvLength then return null`() {
        assertEquals(null, CardBrandDetector.getRequiredCvvLength(INVALID_CARD))
    }

    @Test
    fun `Given VISA valid card number when calling isSupportedCard then return true`() {
        assertTrue(CardBrandDetector.isSupportedCard(VALID_VISA))
    }

    @Test
    fun `Given invalid card number when calling isSupportedCard then return false`() {
        assertFalse(CardBrandDetector.isSupportedCard(INVALID_CARD))
    }

    @Test
    fun `Given invalid non digits card number when calling isSupportedCard then return false`() {
        assertFalse(CardBrandDetector.isSupportedCard(INVALID_NON_DIGITS))
    }

    @Test
    fun `Given invalid short card number when calling isSupportedCard then return false`() {
        assertFalse(CardBrandDetector.isSupportedCard(INVALID_SHORT))
    }

    @Test
    fun `Given invalid too long card number when calling isSupportedCard then return false`() {
        assertFalse(CardBrandDetector.isSupportedCard(INVALID_TOO_LONG))
    }
}