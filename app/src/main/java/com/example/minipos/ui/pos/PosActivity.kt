package com.example.minipos.ui.pos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minipos.R
import com.example.minipos.data.model.Product
import com.example.minipos.data.repository.CartRepository
import com.example.minipos.data.repository.ProductRepository
import com.example.minipos.ui.login.LoginActivity

class PosActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var recyclerProducts: RecyclerView
    private lateinit var btnCart: Button
    private lateinit var txtTotal: TextView
    private lateinit var btnLogout: Button

    private val productRepository = ProductRepository()

    private lateinit var allProducts: List<Product>
    private lateinit var adapter: ProductAdapter
    private lateinit var btnTransactions: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pos)

        edtSearch = findViewById(R.id.edtSearch)
        recyclerProducts = findViewById(R.id.recyclerProducts)
        btnCart = findViewById(R.id.btnCart)
        txtTotal = findViewById(R.id.txtTotal)
        btnLogout = findViewById(R.id.btnLogout)

        recyclerProducts.layoutManager =
            LinearLayoutManager(this)

        // Lấy danh sách sản phẩm
        allProducts =
            productRepository.getProducts()

        // Khởi tạo adapter TRƯỚC
        adapter = ProductAdapter(
            allProducts.toMutableList()
        ) {
            updateCart()
        }

        recyclerProducts.adapter = adapter

        setupSearch()

        btnCart.setOnClickListener {

            val intent = Intent(
                this,
                CartActivity::class.java
            )

            startActivity(intent)
        }

        btnLogout.setOnClickListener {

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            startActivity(intent)

            finish()
        }

        updateCart()

        btnTransactions =
            findViewById(R.id.btnTransactions)
        btnTransactions.setOnClickListener {

            val intent = Intent(
                this,
                TransactionActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Khi quay lại từ CartActivity hoặc PaymentActivity
        // adapter lúc này chắc chắn đã được khởi tạo
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }

        if (::btnCart.isInitialized) {
            updateCart()
        }
    }

    private fun setupSearch() {

        edtSearch.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    val keyword =
                        s.toString().trim()

                    if (keyword.isEmpty()) {

                        adapter.updateProducts(
                            allProducts
                        )

                    } else {

                        val filteredProducts =
                            allProducts.filter { product ->

                                product.name.contains(
                                    keyword,
                                    ignoreCase = true
                                )
                            }

                        adapter.updateProducts(
                            filteredProducts
                        )
                    }
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )
    }

    private fun updateCart() {

        val quantity =
            CartRepository.getTotalQuantity()

        val total =
            CartRepository.getTotal()

        btnCart.text =
            "XEM GIỎ HÀNG ($quantity)"

        txtTotal.text =
            "Tổng: $total đ"
    }
}