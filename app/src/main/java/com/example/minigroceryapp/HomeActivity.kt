package com.example.minigroceryapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.adapter.ProductAdapter
import com.example.minigroceryapp.model.Product
import com.example.minigroceryapp.utils.ProductData
import com.example.minigroceryapp.viewmodel.CartViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var btnCart: Button

    private lateinit var btnAll: Button
    private lateinit var btnDairy: Button
    private lateinit var btnBakery: Button
    private lateinit var btnFruits: Button
    private lateinit var btnGroceries: Button

    private lateinit var adapter: ProductAdapter
    private lateinit var productList: List<Product>

    private val cartViewModel = CartViewModel.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Views
        recyclerView = findViewById(R.id.recyclerViewProducts)
        etSearch = findViewById(R.id.etSearch)
        btnCart = findViewById(R.id.btnCart)

        btnAll = findViewById(R.id.btnAll)
        btnDairy = findViewById(R.id.btnDairy)
        btnBakery = findViewById(R.id.btnBakery)
        btnFruits = findViewById(R.id.btnFruits)
        btnGroceries = findViewById(R.id.btnGroceries)

        // Data
        productList = ProductData.getProducts()

        // Adapter
        adapter = ProductAdapter(productList) { product ->
            cartViewModel.addToCart(product)
            Toast.makeText(this, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        // Search
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterProducts()
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) { }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) { }
        })

        // Category Buttons
        btnAll.setOnClickListener { filterByCategory("All") }
        btnDairy.setOnClickListener { filterByCategory("Dairy") }
        btnBakery.setOnClickListener { filterByCategory("Bakery") }
        btnFruits.setOnClickListener { filterByCategory("Fruits") }
        btnGroceries.setOnClickListener { filterByCategory("Groceries") }

        // Cart
        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun filterProducts() {
        val query = etSearch.text.toString().trim().lowercase()

        val filteredList = productList.filter {
            it.name.lowercase().contains(query) ||
                    it.category.lowercase().contains(query)
        }

        adapter.updateList(filteredList)
    }

    private fun filterByCategory(category: String) {
        if (category == "All") {
            adapter.updateList(productList)
        } else {
            val filteredList = productList.filter {
                it.category.equals(category, ignoreCase = true)
            }
            adapter.updateList(filteredList)
        }
    }
}