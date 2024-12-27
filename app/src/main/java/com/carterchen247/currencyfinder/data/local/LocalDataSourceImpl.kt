package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.data.local.db.dao.CurrencyDataDao
import com.carterchen247.currencyfinder.data.local.db.entity.toCurrencyData
import com.carterchen247.currencyfinder.data.local.db.entity.toCurrencyDataEntity
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.util.CurrencyFilter
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProvider

class LocalDataSourceImpl(
    private val currencyDataDao: CurrencyDataDao,
    private val currencyResourceProvider: CurrencyResourceProvider,
) : LocalDataSource {
    private val currencyFilter = CurrencyFilter()

    override suspend fun loadData() {
        clearData()

        val currencyDataset = currencyResourceProvider.getDataset(CurrencyType.CRYPTO) + currencyResourceProvider.getDataset(CurrencyType.FIAT)
        val currencyDataEntities = currencyDataset.map { it.toCurrencyDataEntity() }

        currencyDataDao.insertAll(currencyDataEntities)
    }

    override suspend fun searchCurrency(input: String, currencyType: CurrencyType?): List<CurrencyData> {
        val currencyDataList = currencyDataDao.selectAll().map { it.toCurrencyData() }
        val filteredList = when (currencyType) {
            null -> currencyDataList
            else -> currencyDataList.filter { it.type == currencyType }
        }
        return currencyFilter.filter(input, filteredList).map { it as CurrencyData }
    }

    override suspend fun clearData() {
        currencyDataDao.deleteAll()
    }
}