package com.carterchen247.currencyfinder.ui.model

data class CurrencyInfo(
    val simpleCode: String, // TODO: add convert logic from fullCode to simpleCode
    val name: String,
    val fullCode: String,
)