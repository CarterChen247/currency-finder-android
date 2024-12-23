package com.carterchen247.currencyfinder.model

data class CurrencyData(
    val id: String,
    override val name: String,
    override val symbol: String,
    val code: String,
    val type: CurrencyType
) : SearchableCurrency