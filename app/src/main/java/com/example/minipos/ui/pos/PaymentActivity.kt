package com.example.minipos.ui.pos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minipos.R
import com.example.minipos.data.model.CartItem
import com.example.minipos.data.model.Transaction
import com.example.minipos.data.repository.CartRepository
import com.example.minipos.data.repository.TransactionRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentActivity : AppCompatActivity() {

    private lateinit var txtTotal: TextView
    private lateinit var btnCash: Button
    private lateinit var btnQr: Button
    private lateinit var btnCard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        txtTotal = findViewById(R.id.txtTotal)
        btnCash = findViewById(R.id.btnCash)
        btnQr = findViewById(R.id.btnQr)
        btnCard = findViewById(R.id.btnCard)

        val total = CartRepository.getTotal()

        txtTotal.text =
            "Tổng thanh toán: $total đ"

        btnCash.setOnClickListener {
            processPayment("Tiền mặt")
        }

        btnQr.setOnClickListener {
            processPayment("QR Code")
        }

        btnCard.setOnClickListener {
            processPayment("Thẻ")
        }
    }

    private fun processPayment(method: String) {

        val total = CartRepository.getTotal()

        // Lưu lại danh sách sản phẩm trước khi clear cart
        val items = CartRepository.getItems().map {
            CartItem(
                product = it.product,
                quantity = it.quantity
            )
        }

        val transaction = Transaction(
            id = "TX${System.currentTimeMillis()}",
            time = SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss",
                Locale.getDefault()
            ).format(Date()),
            total = total,
            paymentMethod = method,
            items = items
        )

        TransactionRepository.addTransaction(transaction)

        CartRepository.clearCart()

        val intent = Intent(
            this,
            PosActivity::class.java
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

        startActivity(intent)

        finish()
    }
}
