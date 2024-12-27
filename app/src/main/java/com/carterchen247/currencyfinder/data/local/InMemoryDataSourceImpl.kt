package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.util.CurrencyFilter
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProvider

class InMemoryDataSourceImpl(
    private val currencyResourceProvider: CurrencyResourceProvider,
) : LocalDataSource {
    private val currencyFilter = CurrencyFilter()
    private val currencyDataList = mutableListOf<CurrencyData>()

    override suspend fun loadData() {
        clearData()

        val currencyDataset = currencyResourceProvider.getDataset(CurrencyType.CRYPTO) + currencyResourceProvider.getDataset(CurrencyType.FIAT)

        currencyDataList.addAll(currencyDataset)
    }

    override suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType?
    ): List<CurrencyData> {
        val filteredList = when (currencyType) {
            null -> currencyDataList
            else -> currencyDataList.filter { it.type == currencyType }
        }
        return currencyFilter.filter(input, filteredList).map { it as CurrencyData }
    }

    override suspend fun clearData() {
        currencyDataList.clear()
    }
}