package com.example.minigroceryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minigroceryapp.viewmodel.CartViewModel

class CheckoutActivity : AppCompatActivity() {

    private lateinit var addressEditText: EditText
    private lateinit var paymentRadioGroup: RadioGroup
    private lateinit var placeOrderButton: Button

    private val cartViewModel = CartViewModel.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        addressEditText = findViewById(R.id.etAddress)
        paymentRadioGroup = findViewById(R.id.radioGroupPayment)
        placeOrderButton = findViewById(R.id.btnPlaceOrder)

        placeOrderButton.setOnClickListener {
            val address = addressEditText.text.toString().trim()

            if (address.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter delivery address",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (paymentRadioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Please select a payment method",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val orderId = "ORD${System.currentTimeMillis()}"
            val total = cartViewModel.getTotalAmount()

            cartViewModel.clearCart()

            val intent = Intent(this, OrderSuccessActivity::class.java)
            intent.putExtra("orderId", orderId)
            intent.putExtra("total", total)
            startActivity(intent)

            finish()
        }
    }
}