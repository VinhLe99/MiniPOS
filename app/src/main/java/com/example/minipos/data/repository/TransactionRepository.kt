package com.example.minipos.data.repository

import com.example.minipos.data.model.Transaction

object TransactionRepository {

    private val transactions =
        mutableListOf<Transaction>()

    fun addTransaction(
        transaction: Transaction
    ) {
        transactions.add(
            0,
            transaction
        )
    }

    fun getTransactions(): List<Transaction> {
        return transactions
    }

    fun getTransactionById(id: String): Transaction? {
        return transactions.find {
            it.id == id
        }
    }
}