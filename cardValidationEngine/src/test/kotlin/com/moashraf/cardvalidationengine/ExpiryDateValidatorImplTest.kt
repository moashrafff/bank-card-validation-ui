package com.moashraf.cardvalidationengine


import com.moashraf.cardvalidationengine.api.ExpiryDateValidator
import com.moashraf.cardvalidationengine.model.ExpiryValidationResult
import org.junit.Assert.assertEquals
import org.junit.Test


class ExpiryDateValidatorImplTest {

    @Test
    fun `Given a valid expiry date, when validate is called, then ExpiryValidationResult_Valid is returned`() {
        assertEquals(ExpiryValidationResult.Valid, ExpiryDateValidator.validate("12-25"))
    }

    @Test
    fun `Given an invalid expiry date, when validate is called, then ExpiryValidationResult_Invalid is returned`() {
        assertEquals(ExpiryValidationResult.InvalidFormat, ExpiryDateValidator.validate("12243"))
    }

    @Test
    fun `Given an empty expiry date, when validate is called, then ExpiryValidationResult_Invalid is returned`() {
        assertEquals(ExpiryValidationResult.InvalidFormat, ExpiryDateValidator.validate(""))
    }

    @Test
    fun `Given an invalid month in expiry date, when validate is called, then ExpiryValidationResult_Invalid_Month is returned`() {
        assertEquals(ExpiryValidationResult.InvalidMonth, ExpiryDateValidator.validate("1325"))
    }
    @Test
    fun `Given an expired expiry date, when validate is called, then ExpiryValidationResult_Expired is returned`() {
        assertEquals(ExpiryValidationResult.Expired, ExpiryDateValidator.validate("1223"))
    }

    @Test
    fun `Given expiry with non-digit in Month or Year, when validate is called, then ExpiryValidationResult_Invalid_Format is returned`() {
        assertEquals(ExpiryValidationResult.InvalidFormat, ExpiryDateValidator.validate("1a25"))
        assertEquals(ExpiryValidationResult.InvalidFormat, ExpiryDateValidator.validate("12a5"))
    }

    @Test
    fun `Given current month and year, when validate is called, then ExpiryValidationResult_Valid is returned`() {
        val now = java.util.Calendar.getInstance()
        val month = String.format("%02d", now.get(java.util.Calendar.MONTH) + 1)
        val year = (now.get(java.util.Calendar.YEAR) % 100).toString()
        assertEquals(ExpiryValidationResult.Valid, ExpiryDateValidator.validate("$month$year"))
    }

    @Test
    fun `Given a valid expiry with slashes, when validate is called, then ExpiryValidationResult_Valid is returned`() {
        assertEquals(ExpiryValidationResult.Valid, ExpiryDateValidator.validate("12/25"))
    }

}