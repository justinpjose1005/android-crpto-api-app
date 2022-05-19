package com.onjuno.assignment.crypto.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class YourCryptoHoldingsList : ArrayList<YourCryptoHoldingsListItem>()

data class YourCryptoHoldingsListItem(
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("current_bal_in_token")
    val currentBalInToken: String = "",
    @SerializedName("current_bal_in_usd")
    val currentBalInUsd: String = "",
    val stateType: StateType = StateType.INVALID
)

class YourCryptoHoldingsListItemDiffCallback : DiffUtil.ItemCallback<YourCryptoHoldingsListItem>() {
    override fun areItemsTheSame(
        oldItem: YourCryptoHoldingsListItem,
        newItem: YourCryptoHoldingsListItem
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: YourCryptoHoldingsListItem,
        newItem: YourCryptoHoldingsListItem
    ): Boolean = oldItem == newItem
}
