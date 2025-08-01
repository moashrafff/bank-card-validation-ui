package com.bankcardvalidator.cvvValidatorTest

import com.bankcardvalidator.api.CardValidator.isCvvValid
import com.bankcardvalidator.cvvValidationEngine.result.CvvValidationResult
import com.bankcardvalidator.testUtils.CardTestConstants.EMPTY_STRING
import com.bankcardvalidator.testUtils.CardTestConstants.FOUR_DIGIT_CVV
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_TOO_LONG
import com.bankcardvalidator.testUtils.CardTestConstants.THREE_DIGIT_CVV
import com.bankcardvalidator.testUtils.CardTestConstants.THREE_DIGIT_CVV_WITH_CHAR
import com.bankcardvalidator.testUtils.CardTestConstants.THREE_DIGIT_CVV_WITH_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.TWO_DIGIT_CVV
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_AMEX
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DINERS
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DISCOVER
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_JCB
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_MASTERCARD
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_UNIONPAY
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_VISA
import org.junit.Assert.assertEquals
import org.junit.Test

class CvvValidatorTest {
    @Test
    fun `given a valid Visa with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_VISA, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a valid Amex with 4-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_AMEX, FOUR_DIGIT_CVV))
    }

    @Test
    fun `given a valid JCB with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_JCB, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a valid Mastercard with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_MASTERCARD, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a valid Diners with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_DINERS, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a valid UnionPay with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_UNIONPAY, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a valid Discover with 3-digit cvv then return Valid`() {
        assertEquals(CvvValidationResult.Valid, isCvvValid(VALID_DISCOVER, THREE_DIGIT_CVV))
    }

    @Test
    fun `given a Mastercard with 4-digit cvv then return InvalidLength`() {
        assertEquals(CvvValidationResult.InvalidLength, isCvvValid(VALID_MASTERCARD, FOUR_DIGIT_CVV))
    }

    @Test
    fun `given a Visa with 2-digit cvv then return InvalidLength`() {
        assertEquals(CvvValidationResult.InvalidLength, isCvvValid(VALID_VISA, TWO_DIGIT_CVV))
    }

    @Test
    fun `given a Mastercard with alphabetic characters in cvv then return InvalidFormat`() {
        assertEquals(CvvValidationResult.InvalidFormat, isCvvValid(VALID_MASTERCARD, THREE_DIGIT_CVV_WITH_CHAR))
    }

    @Test
    fun `given an invalid card number then return InvalidLength`() {
        assertEquals(CvvValidationResult.InvalidLength, isCvvValid(INVALID_TOO_LONG, FOUR_DIGIT_CVV))
    }

    @Test
    fun `given a valid card with empty cvv then return InvalidLength`() {
        assertEquals(CvvValidationResult.InvalidLength, isCvvValid(VALID_VISA, EMPTY_STRING))
    }

    @Test
    fun `given a valid card with cvv containing spaces then return InvalidFormat`() {
        assertEquals(CvvValidationResult.InvalidFormat, isCvvValid(VALID_VISA, THREE_DIGIT_CVV_WITH_SPACES))
    }

}