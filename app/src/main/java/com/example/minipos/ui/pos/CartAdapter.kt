package com.example.minipos.ui.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.model.CartItem
import com.example.minipos.data.repository.CartRepository

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView =
            itemView.findViewById(R.id.txtCartProductName)

        val txtQuantity: TextView =
            itemView.findViewById(R.id.txtQuantity)

        val txtSubtotal: TextView =
            itemView.findViewById(R.id.txtSubtotal)

        val btnMinus: Button =
            itemView.findViewById(R.id.btnMinus)

        val btnPlus: Button =
            itemView.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_cart,
                parent,
                false
            )

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.txtName.text =
            item.product.name

        holder.txtQuantity.text =
            item.quantity.toString()

        holder.txtSubtotal.text =
            "${item.getSubtotal()} đ"

        holder.btnPlus.setOnClickListener {

            CartRepository.addProduct(
                item.product
            )

            refresh()
        }

        holder.btnMinus.setOnClickListener {

            CartRepository.removeProduct(
                item.product
            )

            refresh()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun refresh() {

        items.clear()

        items.addAll(
            CartRepository.getItems()
        )

        notifyDataSetChanged()

        onCartChanged()
    }
}