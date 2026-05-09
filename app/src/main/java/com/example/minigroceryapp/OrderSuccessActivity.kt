package com.example.minigroceryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        val tvOrderDetails = findViewById<TextView>(R.id.tvOrderDetails)
        val btnBackToHome = findViewById<Button>(R.id.btnBackToHome)

        val orderId = intent.getStringExtra("orderId") ?: "ORD0000"
        val total = intent.getDoubleExtra("total", 0.0)

        tvOrderDetails.text = """
            🎉 Order Placed Successfully!

            Order ID: $orderId
            Estimated Delivery: 15-20 minutes

            Total Amount: ₹$total

            Thank you for shopping with us!
        """.trimIndent()

        btnBackToHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}