package com.asfirr2201.broadstoneassignment

import androidx.core.text.isDigitsOnly

class CardValidator() {

    fun validateAmericanExpressCard(number: String): Boolean {
        if (number.length != 15) {
            return false
        }
        if (number[0] != '3') {
            return false
        }
        if (number[1] != '4') {
            if (number[1] != '7')
                return false
        }
        return true
    }

    fun validateDiscoverCard(number: String): Boolean {
        if (number.length == 16 && number.isDigitsOnly()) {
            val firstSixDigits = number.substring(0, 6).toInt()
            if (firstSixDigits in 601100..601109) {
                return true
            }
            if (firstSixDigits in 601120..601149) {
                return true
            }
            if (firstSixDigits == 601174) {
                return true
            }
            if (firstSixDigits in 601177..601179) {
                return true
            }
            if (firstSixDigits in 601186..601199) {
                return true
            }
            if (firstSixDigits in 644000..659999) {
                return true
            }
        }
        return false
    }

    fun validateMasterCard(number: String): Boolean {
        if (number.length == 16 && number.isDigitsOnly()) {
            if (number[0] == '5') {
                val secondNum = number[1].toString().toInt()
                return secondNum in 1..5
            }
            if (number[0] == '2') {
                val secondNum = number[1].toString().toInt()
                if (secondNum in 2..7) {
                    val firstFiveDigits = number.substring(0, 6).toInt()
                    return firstFiveDigits in 222100..272099
                }
            }
        }
        return false
    }

    fun validateVisaCard(number: String): Boolean {
        if (number.length <= 19 && number.isDigitsOnly()) {
            return number[0] == '4'
        }
        return false
    }

}