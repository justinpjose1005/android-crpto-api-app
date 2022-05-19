package com.onjuno.assignment.crypto.models

import com.google.gson.annotations.SerializedName

data class FeedDataModel(
    @SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance = CryptoBalance(),
    @SerializedName("your_crypto_holdings")
    val yourCryptoHoldingsList: YourCryptoHoldingsList = YourCryptoHoldingsList(),
    @SerializedName("crypto_prices")
    val cryptoPricesList: CryptoPricesList = CryptoPricesList(),
    @SerializedName("all_transactions")
    val allTransactionsList: AllTransactionsList = AllTransactionsList(),
)
