package com.example.minipos.data.model

data class CartItem(
    val product: Product,
    var quantity: Int
) {
    fun getSubtotal(): Long {
        return product.price * quantity
    }
}