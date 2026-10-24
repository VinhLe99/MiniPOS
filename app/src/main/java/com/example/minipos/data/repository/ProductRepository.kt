package com.example.minipos.data.repository

import com.example.minipos.data.model.Product

class ProductRepository {

    fun getProducts(): List<Product> {

        return listOf(
            Product(1, "Coca Cola", 15000),
            Product(2, "Pepsi", 15000),
            Product(3, "Bánh mì", 20000),
            Product(4, "Cà phê", 25000),
            Product(5, "Nước suối", 10000),
            Product(6, "Trà xanh", 12000)
        )
    }
}