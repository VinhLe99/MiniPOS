package com.example.minipos.ui.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.model.Transaction

class TransactionAdapter(
    private val transactions: MutableList<Transaction>,
    private val onTransactionClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtId: TextView =
            itemView.findViewById(R.id.txtTransactionId)

        val txtTime: TextView =
            itemView.findViewById(R.id.txtTransactionTime)

        val txtTotal: TextView =
            itemView.findViewById(R.id.txtTransactionTotal)

        val txtMethod: TextView =
            itemView.findViewById(R.id.txtPaymentMethod)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_transaction,
                parent,
                false
            )

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {

        val transaction = transactions[position]

        holder.txtId.text =
            "Mã: ${transaction.id}"

        holder.txtTime.text =
            "Thời gian: ${transaction.time}"

        holder.txtTotal.text =
            "Tổng tiền: ${transaction.total} đ"

        holder.txtMethod.text =
            "Thanh toán: ${transaction.paymentMethod}"

        holder.itemView.setOnClickListener {
            onTransactionClick(transaction)
        }
    }

    override fun getItemCount(): Int =
        transactions.size
}
