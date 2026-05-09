package com.example.minigroceryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.adapter.CartAdapter
import com.example.minigroceryapp.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotalAmount: TextView
    private lateinit var btnCheckout: Button

    private lateinit var cartAdapter: CartAdapter

    private val cartViewModel = CartViewModel.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recyclerViewCart)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        btnCheckout = findViewById(R.id.btnCheckout)

        cartAdapter = CartAdapter(
            emptyList(),
            onIncrease = { position ->
                cartViewModel.increaseQuantity(position)
            },
            onDecrease = { position ->
                cartViewModel.decreaseQuantity(position)
            },
            onRemove = { position ->
                cartViewModel.removeItem(position)
            }
        )

        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        // Observe cart changes
        cartViewModel.cartItems.observe(this) { items ->
            cartAdapter.updateData(items)
            tvTotalAmount.text = "Total: ₹${cartViewModel.getTotalAmount()}"
        }

        btnCheckout.setOnClickListener {
            if ((cartViewModel.cartItems.value ?: emptyList()).isNotEmpty()) {
                startActivity(Intent(this, CheckoutActivity::class.java))
            }
        }
    }
}