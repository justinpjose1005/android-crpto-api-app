package com.onjuno.assignment.crypto.models

data class FeedDataModel(
    val crypto_balance: CryptoBalance = CryptoBalance(),
    val yourCryptoHoldingsList: YourCryptoHoldingsList = YourCryptoHoldingsList(),
    val cryptoPricesList: CryptoPricesList = CryptoPricesList(),
    val allTransactionsList: AllTransactionsList = AllTransactionsList(),
)
