import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_CARD
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.INVALID_NON_DIGITS
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_MASTERCARD
import com.bankcardvalidator.LuhnUtilsTest.TestCardNumbers.VALID_VISA
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import com.bankcardvalidator.typoValidationEngine.isLuhnValid

class LuhnValidatorTest {

    @Test
    fun `given a valid Visa card number, when validated with isLuhnValid, then it should returns true`() {
        assertTrue(isLuhnValid(VALID_VISA))
    }

    @Test
    fun `given a valid Mastercard number, when validated with isLuhnValid, then it should returns true`() {
        assertTrue(isLuhnValid(VALID_MASTERCARD))
    }

    @Test
    fun `given an invalid card number, when validated with isLuhnValid, then it should returns false`() {
        assertFalse(isLuhnValid(INVALID_CARD))
    }

    @Test
    fun `given a card number with non-digits, when validated with isLuhnValid, then it should ignore it and return true`() {
        assertTrue(isLuhnValid(INVALID_NON_DIGITS))
    }

}