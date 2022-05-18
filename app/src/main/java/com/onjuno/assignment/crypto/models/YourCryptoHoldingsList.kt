package com.onjuno.assignment.crypto.models

class YourCryptoHoldingsList : ArrayList<YourCryptoHoldingsListItem>()

data class YourCryptoHoldingsListItem(
    val logo: String = "",
    val title: String = "",
    val currentBalInToken: String = "",
    val currentBalInUsd: String = "",
)
