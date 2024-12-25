package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.data.local.db.dao.CurrencyDataDao
import com.carterchen247.currencyfinder.data.local.db.entity.toCurrencyData
import com.carterchen247.currencyfinder.data.local.db.entity.toCurrencyDataEntity
import com.carterchen247.currencyfinder.data.local.model.CurrencyDataModel
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.util.CurrencyFilter
import com.google.gson.Gson

class LocalDataSourceImpl(
    private val currencyDataDao: CurrencyDataDao
) : LocalDataSource {
    private val gson = Gson()
    private val currencyFilter = CurrencyFilter()

    override suspend fun loadData() {
        clearData()

        val currencyListCrypto = gson.fromJson(Dataset.CRYPTO, Array<CurrencyDataModel>::class.java)
            .map { currencyDataModel ->
                currencyDataModel.toCurrencyDataEntity(CurrencyType.CRYPTO)
            }

        val currencyListFiat = gson.fromJson(Dataset.FIAT, Array<CurrencyDataModel>::class.java)
            .map { currencyDataModel ->
                currencyDataModel.toCurrencyDataEntity(CurrencyType.FIAT)
            }

        currencyDataDao.insertAll(currencyListCrypto + currencyListFiat)
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