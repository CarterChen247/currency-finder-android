package com.carterchen247.currencyfinder.data

import com.carterchen247.currencyfinder.data.local.LocalDataSource
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
) : Repository {

    override suspend fun loadData() {
        localDataSource.loadData()
    }

    override suspend fun clearData() {
        localDataSource.clearData()
    }

    override suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType?
    ): List<CurrencyData> {
        return localDataSource.searchCurrency(input, currencyType)
    }
}