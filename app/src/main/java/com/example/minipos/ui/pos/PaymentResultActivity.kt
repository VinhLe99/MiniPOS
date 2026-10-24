package com.example.minipos.ui.pos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minipos.R
import com.example.minipos.data.repository.CartRepository

class PaymentResultActivity : AppCompatActivity() {

    private lateinit var txtStatus: TextView
    private lateinit var txtMethod: TextView
    private lateinit var txtTotal: TextView
    private lateinit var btnDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_payment_result)

        txtStatus = findViewById(R.id.txtStatus)
        txtMethod = findViewById(R.id.txtMethod)
        txtTotal = findViewById(R.id.txtTotal)
        btnDone = findViewById(R.id.btnDone)

        val paymentMethod =
            intent.getStringExtra("payment_method")
                ?: "Không xác định"

        val total =
            CartRepository.getTotal()

        txtMethod.text =
            "Phương thức: $paymentMethod"

        txtTotal.text =
            "Số tiền: $total đ"

        btnDone.isEnabled = false

        startPayment(paymentMethod)

        btnDone.setOnClickListener {

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

    private fun startPayment(method: String) {

        txtStatus.text =
            "Đang xử lý thanh toán..."

        Handler(Looper.getMainLooper()).postDelayed({

            // Mô phỏng thanh toán thành công
            txtStatus.text =
                "THANH TOÁN THÀNH CÔNG"

            btnDone.isEnabled = true

        }, 2000)
    }
}