package com.bankcardvalidator.LuhnUtilsTest

internal object TestCardNumbers {
    const val VALID_VISA="4539 1488 0343 6467"
    const val VALID_MASTERCARD="5555 5555 5555 4444"
    const val VALID_AMEX = "3782 822463 10005"
    const val VALID_DISCOVER = "6011 0009 9013 9424"
    const val VALID_UNIONPAY = "6221 0000 0000 0000"
    const val VALID_JCB = "3530 1113 3330 0000"
    const val VALID_DINERS = "3056 9309 0259 04"
    const val INVALID_CARD="1234 5678 9012 3456"
    const val INVALID_NON_DIGITS="4539-1488-0343-6467"
    const val INVALID_SHORT = "4111 1111"
    const val INVALID_TOO_LONG = "4111 1111 1111 1111 9999"
}