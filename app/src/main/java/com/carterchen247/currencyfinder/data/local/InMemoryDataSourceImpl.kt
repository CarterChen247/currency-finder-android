package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.data.local.model.CurrencyDataModel
import com.carterchen247.currencyfinder.data.local.model.toCurrencyData
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.util.CurrencyFilter
import com.google.gson.Gson

class InMemoryDataSourceImpl : LocalDataSource {
    private val gson = Gson()
    private val currencyFilter = CurrencyFilter()
    private val currencyDataList = mutableListOf<CurrencyData>()

    override suspend fun loadData() {
        clearData()

        val currencyListCrypto = gson.fromJson(Dataset.CRYPTO, Array<CurrencyDataModel>::class.java)
            .map { currencyDataModel ->
                currencyDataModel.toCurrencyData(CurrencyType.CRYPTO)
            }
        currencyDataList.addAll(currencyListCrypto)

        val currencyListFiat = gson.fromJson(Dataset.FIAT, Array<CurrencyDataModel>::class.java)
            .map { currencyDataModel ->
                currencyDataModel.toCurrencyData(CurrencyType.FIAT)
            }
        currencyDataList.addAll(currencyListFiat)
    }

    override suspend fun searchCurrency(
        input: String,
        currencyType: CurrencyType?
    ): List<CurrencyData> {
        val filteredList = when (currencyType){
            null -> currencyDataList
            else -> currencyDataList.filter { it.type == currencyType }
        }
        return currencyFilter.filter(input, filteredList).map { it as CurrencyData }
    }

    override suspend fun clearData() {
        currencyDataList.clear()
    }
}