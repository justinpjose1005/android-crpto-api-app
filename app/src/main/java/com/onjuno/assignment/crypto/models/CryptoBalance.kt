package com.onjuno.assignment.crypto.models

import com.google.gson.annotations.SerializedName

data class CryptoBalance(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("subtitle")
    val subTitle: String = "",
    @SerializedName("current_bal_in_usd")
    val currentBalInUsd: String = "",
)
