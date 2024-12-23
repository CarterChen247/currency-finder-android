package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

interface LocalDataSource {
    suspend fun loadData()

    suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType? = null
    ): List<CurrencyData>

    suspend fun clearData()
}