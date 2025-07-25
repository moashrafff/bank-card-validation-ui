import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import utils.isLuhnValid

class LuhnValidatorTest {

    @Test
    fun `when Visa card number is valid, isLuhnValid should returns true`() {
        val cardNumber = "4539 1488 0343 6467"
        assertTrue(isLuhnValid(cardNumber))
    }

    @Test
    fun `when Mastercard number is valid, isLuhnValid should returns true`() {
        val cardNumber = "5555 5555 5555 4444"
        assertTrue(isLuhnValid(cardNumber))
    }

    @Test
    fun `when card number is invalid, isLuhnValid should returns false`() {
        val cardNumber = "1234 5678 9012 3456"
        assertFalse(isLuhnValid(cardNumber))
    }

    @Test
    fun `when non-digits are entered, isLuhnValid should ignore it and return true`() {
        val cardNumber = "4539-1488-0343-6467"
        assertTrue(isLuhnValid(cardNumber))
    }

}