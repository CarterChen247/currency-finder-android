package com.carterchen247.currencyfinder.data

import com.carterchen247.currencyfinder.data.local.LocalDataSource
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val dispatcherProvider: DispatcherProvider
) : Repository {

    override suspend fun loadData() = withContext(dispatcherProvider.io) {
        localDataSource.loadData()
    }

    override suspend fun clearData() = withContext(dispatcherProvider.io) {
        localDataSource.clearData()
    }

    override suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType?
    ): List<CurrencyData> = withContext(dispatcherProvider.io) {
        localDataSource.searchCurrency(input, currencyType)
    }
}