package com.asfirr2201.broadstoneassignment

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.asfirr2201.broadstoneassignment.databinding.ActivityMainBinding
import com.asfirr2201.broadstoneassignment.viewmodel.MainFactory
import com.asfirr2201.broadstoneassignment.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val americanExpress = "American Express"
        const val discover = "Discover"
        const val masterCard = "Master Card"
        const val visa = "Visa"
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        MainFactory(CardValidator(), LuhnValidator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardType.setAdapter(getCardTypes())

        binding.submit.setOnClickListener {
            clearErrors()
            if (validateInputs(
                    binding.cardType.text.toString(),
                    binding.cardNumber.text.toString(),
                    binding.expirationDate.text.toString(),
                    binding.cvvCode.text.toString(),
                    binding.firstName.text.toString(),
                    binding.lastName.text.toString()
                )
            ) {
                showAlert("Payment Successful!")
            }
        }

    }

    private fun clearErrors() {
        binding.textInputLayout1.error = null
        binding.textInputLayout2.error = null
        binding.textInputLayout4.error = null
        binding.textInputLayout5.error = null
    }

    private fun showAlert(msg: String) {
        val builder = AlertDialog.Builder(this).apply {
            setIcon(R.drawable.ic_launcher_background)
            setTitle("Success")
            setMessage(msg)
            setPositiveButton("OK") { _: DialogInterface, _: Int -> }
        }
        builder.show()
    }

    private fun validateInputs(
        cardType: String,
        cardNumber: String,
        expDate: String,
        cvvCode: String,
        firstName: String,
        lastName: String
    ): Boolean {
        var success = true
        if (cardType.trim().isEmpty()) {
            binding.textInputLayout.error = "Select a card type!"
            success = false
        }
        if (!viewModel.validateCardNumber(cardNumber, cardType)) {
            binding.textInputLayout1.error = "Invalid Card Number"
            success = false
        }
        if (!viewModel.isExpiryDateValid(expDate)) {
            binding.textInputLayout2.error = "Invalid Expiry Date"
            success = false
        }
        if (!viewModel.validateCVV(cardType, cvvCode)) {
            binding.textInputLayout3.error = "Invalid CVV Code"
            success = false
        }
        if (firstName.isEmpty()) {
            binding.textInputLayout4.error = "Field is Empty"
            success = false
        }
        if (lastName.isEmpty()) {
            binding.textInputLayout5.error = "Field is Empty"
            success = false
        }
        return success
    }

    private fun getCardTypes(): ArrayAdapter<String> {
        val list = listOf<String>(americanExpress, discover, masterCard, visa)
        return ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list)
    }
}