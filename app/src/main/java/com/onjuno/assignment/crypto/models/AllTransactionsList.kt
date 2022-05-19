package com.onjuno.assignment.crypto.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class AllTransactionsList : ArrayList<AllTransactionsListItem>()

data class AllTransactionsListItem(
    @SerializedName("txn_logo")
    val txnLogo: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("txn_time")
    val txnTime: String = "",
    @SerializedName("txn_amount")
    val txnAmount: String = "",
    @SerializedName("txn_sub_amount")
    val txnSubAmount: String = "",
)

class AllTransactionsListItemDiffCallback : DiffUtil.ItemCallback<AllTransactionsListItem>() {
    override fun areItemsTheSame(
        oldItem: AllTransactionsListItem,
        newItem: AllTransactionsListItem
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: AllTransactionsListItem,
        newItem: AllTransactionsListItem
    ): Boolean = oldItem == newItem
}
