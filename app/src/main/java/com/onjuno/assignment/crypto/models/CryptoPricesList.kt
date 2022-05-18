package com.onjuno.assignment.crypto.models

class CryptoPricesList: ArrayList<CryptoPricesListItem>()

data class CryptoPricesListItem(
    val logo: String = "",
    val title: String = "",
    val currentBalInUsd: String = "",
)
