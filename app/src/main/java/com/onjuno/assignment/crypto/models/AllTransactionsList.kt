package com.onjuno.assignment.crypto.models

class AllTransactionsList : ArrayList<AllTransactionsListItem>()

data class AllTransactionsListItem(
    val txn_logo: String = "",
    val title: String = "",
    val txn_time: String = "",
    val txn_amount: String = "",
    val txn_sub_amount: String = "",
)
