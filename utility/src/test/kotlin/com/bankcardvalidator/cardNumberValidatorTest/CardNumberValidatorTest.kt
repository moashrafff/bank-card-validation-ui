package com.bankcardvalidator.cardNumberValidatorTest

import com.bankcardvalidator.api.CardTypoDetector.isValidCardNumber
import com.bankcardvalidator.cardNumberValidationEngine.result.CardNumberValidationResult
import com.bankcardvalidator.testUtils.CardTestConstants.EMPTY_STRING
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_CARD_BRAND
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_FORMAT
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_FORMAT_WITH_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_LUHN
import com.bankcardvalidator.testUtils.CardTestConstants.INVALID_TOO_LONG_WITHOUT_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.SPACES_ONLY
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_AMEX_WITHOUT_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_DISCOVER_WITHOUT_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_MASTERCARD
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_MASTERCARD_WITHOUT_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_UNION_PAY_WITHOUT_SPACES
import com.bankcardvalidator.testUtils.CardTestConstants.VALID_VISA_WITHOUT_SPACES
import org.junit.Assert.assertTrue
import org.junit.Test

class CardNumberValidatorTest {
    @Test
    fun `Given a valid Mastercard without spaces when calling isCardNumberValid then return Valid`() {
        val result = isValidCardNumber(VALID_MASTERCARD_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.Valid == result)
    }

    @Test
    fun `Given a valid visa card number without spaces when calling isCardNumberValid then return Valid`() {
        val result = isValidCardNumber(VALID_VISA_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.Valid == result)
    }

    @Test
    fun `Given a valid UnionPay card number without spaces when calling isCardNumberValid then return Valid`() {
        val result = isValidCardNumber(VALID_UNION_PAY_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.Valid == result)
    }

    @Test
    fun `Given a valid Discover card number without spaces when calling isCardNumberValid then return Valid`() {
        val result = isValidCardNumber(VALID_DISCOVER_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.Valid == result)
    }

    @Test
    fun `Given a valid Amex card number without spaces when calling isCardNumberValid then return Valid`() {
        val result = isValidCardNumber(VALID_AMEX_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.Valid == result)
    }

    @Test
    fun `Given an invalid luhn Visa card number when calling isCardNumberValid then return InvalidLuhn`() {
        val result = isValidCardNumber(INVALID_LUHN)
        assertTrue(CardNumberValidationResult.InvalidLuhn == result)
    }

    @Test
    fun `Given card with alphabetic characters when calling isCardNumberValid then return InvalidFormat`() {
        val result = isValidCardNumber(INVALID_FORMAT)
        assertTrue(CardNumberValidationResult.InvalidFormat == result)
    }

    @Test
    fun `Given a number with only whitespace when calling isCardNumberValid return InvalidFormat`() {
        val result = isValidCardNumber(SPACES_ONLY)
        assertTrue(CardNumberValidationResult.InvalidFormat == result)
    }

    @Test
    fun `Given card number with spaces and alphabetic characters when calling isCardNumberValid then return InvalidFormat`() {
        val result = isValidCardNumber(INVALID_FORMAT_WITH_SPACES)
        assertTrue(CardNumberValidationResult.InvalidFormat == result)
    }

    @Test
    fun `Given a Mastercard with spaces when calling isCardNumberValid then return InvalidFormat`() {
        val result = isValidCardNumber(VALID_MASTERCARD)
        assertTrue(CardNumberValidationResult.InvalidFormat == result)
    }

    @Test
    fun `Given a card number with unsupported prefix when calling isCardNumberValid then return UnknownCardBrand`() {
        val result = isValidCardNumber(INVALID_CARD_BRAND)
        assertTrue(CardNumberValidationResult.UnknownCardBrand == result)
    }

    @Test
    fun `Given an empty string when calling isCardNumberValid then return UnknownCardBrand`() {
        val result = isValidCardNumber(EMPTY_STRING)
        assertTrue(CardNumberValidationResult.UnknownCardBrand == result)
    }

    @Test
    fun `Given an invalid card number when calling isCardNumberValid then return UnknownCardBrand`() {
        val result = isValidCardNumber(INVALID_TOO_LONG_WITHOUT_SPACES)
        assertTrue(CardNumberValidationResult.UnknownCardBrand == result)
    }
}