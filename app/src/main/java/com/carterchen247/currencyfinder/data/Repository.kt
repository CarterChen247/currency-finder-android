package com.carterchen247.currencyfinder.data

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

interface Repository {

    suspend fun loadData()

    suspend fun clearData()

    suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType? = null
    ): List<CurrencyData>
}