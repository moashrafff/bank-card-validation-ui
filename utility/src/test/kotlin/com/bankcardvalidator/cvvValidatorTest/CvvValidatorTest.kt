package com.bankcardvalidator.cvvValidatorTest

import com.bankcardvalidator.testUtils.CardTestConstants.FOUR_DIGIT_CVV
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_TOO_LONG
import com.bankcardvalidator.testUtils.CardTestConstants.THREE_DIGIT_CVV
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_AMEX
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DINERS
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DISCOVER
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_JCB
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_MASTERCARD
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_UNIONPAY
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_VISA
import com.bankcardvalidator.typoValidationEngine.isCvvValid
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CvvValidatorTest {
    @Test
    fun `given a valid Visa card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_VISA, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid Amex card number with 4-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_AMEX, FOUR_DIGIT_CVV))
    }
    @Test
    fun `given a valid jcb card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_JCB, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid Mastercard card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_MASTERCARD, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid Diners card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_DINERS, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid UnionPay card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_UNIONPAY, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid Discover card number with 3-digit cvv when validated with isCvvValid then it should returns true`(){
        assertTrue(isCvvValid(VALID_DISCOVER, THREE_DIGIT_CVV))
    }
    @Test
    fun `given a valid mastercard card number with more than 3-digit cvv when validated with isCvvValid then it should returns false`(){
        assertFalse(isCvvValid(VALID_MASTERCARD, FOUR_DIGIT_CVV))
    }
    @Test
    fun `given a Visa card with 2-digit CVV when validated with isCvvValid then return false`() {
        assertFalse(isCvvValid(VALID_VISA, "12"))
    }
    @Test
    fun `given a valid Mastercard with alphabetic ignored CVV when validated then return true`() {
        assertTrue(isCvvValid(VALID_MASTERCARD, "12A4"))
    }

    @Test
    fun `given a cvv with invalid card number when validated with isCvvValid then it should returns false`(){
        assertFalse(isCvvValid(INVALID_TOO_LONG, FOUR_DIGIT_CVV))
    }
    @Test
    fun `given a valid card with empty CVV when validated then return false`() {
        assertFalse(isCvvValid(VALID_VISA, ""))
    }
    @Test
    fun `given a valid card with CVV containing spaces when validated then return true`() {
        assertTrue(isCvvValid(VALID_VISA, "1 2 3"))
    }

}