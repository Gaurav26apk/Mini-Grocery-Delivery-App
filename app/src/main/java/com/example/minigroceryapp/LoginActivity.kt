package com.example.minigroceryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etMobile: EditText
    private lateinit var etOtp: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etMobile = findViewById(R.id.etMobile)
        etOtp = findViewById(R.id.etOtp)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val mobile = etMobile.text.toString().trim()
            val otp = etOtp.text.toString().trim()

            when {
                mobile.length != 10 -> {
                    Toast.makeText(
                        this,
                        "Please enter a valid 10-digit mobile number",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                otp != "1234" -> {
                    Toast.makeText(
                        this,
                        "Invalid OTP. Use 1234",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    // Open HomeActivity
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                    finish()
                }
            }
        }
    }
}