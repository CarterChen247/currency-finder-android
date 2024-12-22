package com.carterchen247.currencyfinder.util

import com.carterchen247.currencyfinder.model.SearchableCurrency

class CurrencyFilter(private val searchableCurrencyList: List<SearchableCurrency>) {
    fun filter(input: String): List<SearchableCurrency> {
        return searchableCurrencyList.filter {
            it.name.startsWith(input, ignoreCase = true)
        }
    }
}