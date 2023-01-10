package com.asfirr2201.broadstoneassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asfirr2201.broadstoneassignment.CardValidator
import com.asfirr2201.broadstoneassignment.LuhnValidator

class MainFactory(private val validator: CardValidator, private val luHn: LuhnValidator) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(validator, luHn) as T
    }

}