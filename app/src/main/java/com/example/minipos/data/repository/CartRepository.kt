package com.example.minipos.data.repository

import com.example.minipos.data.model.CartItem
import com.example.minipos.data.model.Product

object CartRepository {

    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(product: Product) {

        val existingItem = cartItems.find {
            it.product.id == product.id
        }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(
                CartItem(
                    product = product,
                    quantity = 1
                )
            )
        }
    }

    fun removeProduct(product: Product) {

        val existingItem = cartItems.find {
            it.product.id == product.id
        }

        if (existingItem != null) {

            existingItem.quantity--

            if (existingItem.quantity <= 0) {
                cartItems.remove(existingItem)
            }
        }
    }

    fun getItems(): List<CartItem> {
        return cartItems
    }

    fun getTotal(): Long {
        return cartItems.sumOf {
            it.getSubtotal()
        }
    }

    fun getTotalQuantity(): Int {
        return cartItems.sumOf {
            it.quantity
        }
    }

    fun clearCart() {
        cartItems.clear()
    }
}