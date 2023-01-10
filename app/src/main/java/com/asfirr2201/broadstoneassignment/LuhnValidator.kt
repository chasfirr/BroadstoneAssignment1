package com.asfirr2201.broadstoneassignment

class LuhnValidator {

    fun isValid(cardNumber: String): Boolean {

        val checkDigit = cardNumber[cardNumber.length - 1].digitToInt()
        val payload = cardNumber.substring(0, cardNumber.length - 1)
        val reverse = StringBuilder(payload).reverse()

        var doMultiply = true
        var sum1 = 0
        var sum2 = 0

        for (i in reverse.indices) {
            val digit = reverse[i].digitToInt()
            if (doMultiply) {
                var net = digit * 2
                if (net > 10) {
                    net = (net / 10) + (net % 10)
                }
                sum1 += net
            } else {
                sum2 += digit
            }
            doMultiply = doMultiply.not()
        }
        return (sum1 + sum2) % 10 == checkDigit
    }

}