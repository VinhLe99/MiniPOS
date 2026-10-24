package com.example.minipos.ui.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.model.CartItem

class TransactionDetailAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<TransactionDetailAdapter.DetailViewHolder>() {

    class DetailViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtProductName: TextView =
            itemView.findViewById(
                R.id.txtProductName
            )

        val txtQuantity: TextView =
            itemView.findViewById(
                R.id.txtQuantity
            )

        val txtPrice: TextView =
            itemView.findViewById(
                R.id.txtPrice
            )

        val txtSubtotal: TextView =
            itemView.findViewById(
                R.id.txtSubtotal
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_transaction_detail,
                    parent,
                    false
                )

        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DetailViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.txtProductName.text =
            item.product.name

        holder.txtQuantity.text =
            "SL: ${item.quantity}"

        holder.txtPrice.text =
            "Đơn giá: ${item.product.price} đ"

        holder.txtSubtotal.text =
            "Thành tiền: ${item.getSubtotal()} đ"
    }

    override fun getItemCount(): Int =
        items.size
}
