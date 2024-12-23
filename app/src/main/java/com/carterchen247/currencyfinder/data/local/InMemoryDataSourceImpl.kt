package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

class InMemoryDataSourceImpl : LocalDataSource {
    override suspend fun loadData() {
        TODO("Not yet implemented")
    }

    override suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType?
    ): List<CurrencyData> {
        TODO("Not yet implemented")
    }

    override suspend fun clearData() {
        TODO("Not yet implemented")
    }
}