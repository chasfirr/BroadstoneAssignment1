package com.asfirr2201.broadstoneassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.asfirr2201.broadstoneassignment.CardValidator
import com.asfirr2201.broadstoneassignment.LuhnValidator
import com.asfirr2201.broadstoneassignment.MainActivity

class MainViewModel(private val validator: CardValidator, private val luHn: LuhnValidator) :
    ViewModel() {

    fun validateCardNumber(number: String, cardType: String): Boolean {

        if (number.isEmpty()) {
            return false
        }

        if (!validateWithLUHN(number)) {
            return false
        }

        when (cardType) {
            MainActivity.americanExpress -> {
                return validator.validateAmericanExpressCard(number)
            }
            MainActivity.discover -> {
                return validator.validateDiscoverCard(number)
            }
            MainActivity.masterCard -> {
                return validator.validateMasterCard(number)
            }
            MainActivity.visa -> {
                return validator.validateVisaCard(number)
            }
        }
        return false
    }

    private fun validateWithLUHN(number: String): Boolean {
        return luHn.isValid(number)
    }

    fun validateCVV(cardType: String, key: String): Boolean {
        when (cardType) {
            MainActivity.visa, MainActivity.discover, MainActivity.masterCard -> {
                return key.length == 3
            }
            MainActivity.americanExpress -> {
                return key.length == 4
            }
        }
        return false
    }

    fun isExpiryDateValid(date: String): Boolean {
        if (date.isNotEmpty() && date.length == 5) {
            return date.matches("^(0[1-9]|1[0-2])/?([0-9]{2})$".toRegex())
        }
        return false
    }

}