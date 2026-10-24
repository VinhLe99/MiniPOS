package com.example.minipos.ui.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.model.Product
import com.example.minipos.data.repository.CartRepository

class ProductAdapter(
    private val products: MutableList<Product>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val txtProductName: TextView =
            itemView.findViewById(R.id.txtProductName)

        val txtProductPrice: TextView =
            itemView.findViewById(R.id.txtProductPrice)

        val txtQuantity: TextView =
            itemView.findViewById(R.id.txtQuantity)

        val btnMinus: Button =
            itemView.findViewById(R.id.btnMinus)

        val btnAdd: Button =
            itemView.findViewById(R.id.btnAdd)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product,
                parent,
                false
            )

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = products[position]

        holder.txtProductName.text =
            product.name

        holder.txtProductPrice.text =
            "${product.price} đ"

        val cartItem = CartRepository.getItems()
            .find {
                it.product.id == product.id
            }

        val quantity =
            cartItem?.quantity ?: 0

        holder.txtQuantity.text =
            quantity.toString()

        holder.btnAdd.setOnClickListener {

            CartRepository.addProduct(product)

            notifyItemChanged(position)

            onCartChanged()
        }

        holder.btnMinus.setOnClickListener {

            CartRepository.removeProduct(product)

            notifyItemChanged(position)

            onCartChanged()
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(
        newProducts: List<Product>
    ) {
        products.clear()
        products.addAll(newProducts)

        notifyDataSetChanged()
    }
}