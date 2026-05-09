package com.example.minigroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.R
import com.example.minigroceryapp.model.CartItem
import android.widget.ImageButton

class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onIncrease: (Int) -> Unit,
    private val onDecrease: (Int) -> Unit,
    private val onRemove: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgCartProduct)
        val tvName: TextView = itemView.findViewById(R.id.tvCartProductName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartPrice)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val btnIncrease: ImageButton = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: ImageButton = itemView.findViewById(R.id.btnDecrease)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.imgProduct.setImageResource(cartItem.product.imageResId)
        holder.tvName.text = cartItem.product.name
        holder.tvPrice.text = "₹${cartItem.product.price}"
        holder.tvQuantity.text = cartItem.quantity.toString()

        holder.btnIncrease.setOnClickListener {
            onIncrease(position)
        }

        holder.btnDecrease.setOnClickListener {
            onDecrease(position)
        }

        holder.btnRemove.setOnClickListener {
            onRemove(position)
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateData(newItems: List<CartItem>) {
        cartItems = newItems
        notifyDataSetChanged()
    }
}