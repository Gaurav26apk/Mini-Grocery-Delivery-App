package com.example.minigroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.R
import com.example.minigroceryapp.model.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.imgProduct.setImageResource(product.imageResId)
        holder.tvProductName.text = product.name
        holder.tvCategory.text = product.category
        holder.tvPrice.text = "₹${product.price}"

        holder.btnAddToCart.setOnClickListener {
            onAddToCartClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}