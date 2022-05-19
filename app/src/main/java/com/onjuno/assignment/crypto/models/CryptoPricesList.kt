package com.onjuno.assignment.crypto.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class CryptoPricesList: ArrayList<CryptoPricesListItem>()

data class CryptoPricesListItem(
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("current_price_in_usd")
    val currentPriceInUsd: String = "",
    val stateType: StateType = StateType.INVALID
)

class CryptoPricesListItemDiffCallback : DiffUtil.ItemCallback<CryptoPricesListItem>() {
    override fun areItemsTheSame(
        oldItem: CryptoPricesListItem,
        newItem: CryptoPricesListItem
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: CryptoPricesListItem,
        newItem: CryptoPricesListItem
    ): Boolean = oldItem == newItem
}
