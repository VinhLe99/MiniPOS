package com.example.minipos.ui.pos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.repository.TransactionRepository

class TransactionActivity : AppCompatActivity() {

    private lateinit var recyclerTransactions: RecyclerView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_transaction
        )

        recyclerTransactions =
            findViewById(
                R.id.recyclerTransactions
            )

        btnBack =
            findViewById(
                R.id.btnBack
            )

        recyclerTransactions.layoutManager =
            LinearLayoutManager(this)

        loadTransactions()

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::recyclerTransactions.isInitialized) {
            loadTransactions()
        }
    }

    private fun loadTransactions() {

        val transactions =
            TransactionRepository
                .getTransactions()

        val adapter = TransactionAdapter(
            transactions.toMutableList()
        ) { transaction ->

            val intent = Intent(
                this,
                TransactionDetailActivity::class.java
            )

            intent.putExtra(
                "transactionId",
                transaction.id
            )

            startActivity(intent)
        }

        recyclerTransactions.adapter =
            adapter
    }
}