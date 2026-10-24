package com.example.minipos.data.model

data class Transaction(
    val id: String,
    val time: String,
    val total: Long,
    val paymentMethod: String,
    val items: List<CartItem>
)