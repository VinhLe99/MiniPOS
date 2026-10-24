package com.example.minipos.ui.pos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.repository.TransactionRepository

class TransactionDetailActivity : AppCompatActivity() {

    private lateinit var txtTransactionId: TextView
    private lateinit var txtTransactionTime: TextView
    private lateinit var txtPaymentMethod: TextView
    private lateinit var txtTotal: TextView

    private lateinit var recyclerItems: RecyclerView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_transaction_detail
        )

        txtTransactionId =
            findViewById(R.id.txtTransactionId)

        txtTransactionTime =
            findViewById(R.id.txtTransactionTime)

        txtPaymentMethod =
            findViewById(R.id.txtPaymentMethod)

        txtTotal =
            findViewById(R.id.txtTotal)

        recyclerItems =
            findViewById(R.id.recyclerItems)

        btnBack =
            findViewById(R.id.btnBack)

        recyclerItems.layoutManager =
            LinearLayoutManager(this)

        val transactionId =
            intent.getStringExtra("transactionId")

        if (transactionId == null) {
            finish()
            return
        }

        val transaction =
            TransactionRepository.getTransactionById(
                transactionId
            )

        if (transaction == null) {
            finish()
            return
        }

        txtTransactionId.text =
            "Mã giao dịch: ${transaction.id}"

        txtTransactionTime.text =
            "Thời gian: ${transaction.time}"

        txtPaymentMethod.text =
            "Thanh toán: ${transaction.paymentMethod}"

        txtTotal.text =
            "Tổng tiền: ${transaction.total} đ"

        val adapter =
            TransactionDetailAdapter(
                transaction.items
            )

        recyclerItems.adapter = adapter

        btnBack.setOnClickListener {
            finish()
        }
    }
}
