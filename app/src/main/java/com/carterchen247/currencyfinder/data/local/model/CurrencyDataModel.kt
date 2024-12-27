package com.carterchen247.currencyfinder.data.local.model

data class CurrencyDataModel(
    val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null,
)