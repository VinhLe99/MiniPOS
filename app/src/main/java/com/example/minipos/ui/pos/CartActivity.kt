package com.example.minipos.ui.pos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.repository.CartRepository

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerCart: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var btnCheckout: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cart)

        recyclerCart = findViewById(R.id.recyclerCart)
        txtTotal = findViewById(R.id.txtTotal)
        btnCheckout = findViewById(R.id.btnCheckout)
        btnBack = findViewById(R.id.btnBack)

        recyclerCart.layoutManager =
            LinearLayoutManager(this)

        val adapter = CartAdapter(
            CartRepository.getItems().toMutableList()
        ) {
            updateTotal()
        }

        recyclerCart.adapter = adapter

        updateTotal()

        btnBack.setOnClickListener {
            finish()
        }

        btnCheckout.setOnClickListener {

            if (CartRepository.getItems().isEmpty()) {
                return@setOnClickListener
            }

            val intent = Intent(
                this,
                PaymentActivity::class.java
            )

            startActivity(intent)
        }
    }

    private fun updateTotal() {
        txtTotal.text =
            "Tổng: ${CartRepository.getTotal()} đ"
    }
}