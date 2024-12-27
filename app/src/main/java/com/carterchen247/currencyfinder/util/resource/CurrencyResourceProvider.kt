package com.carterchen247.currencyfinder.util.resource

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

interface CurrencyResourceProvider {

    fun getDataset(currencyType: CurrencyType): List<CurrencyData>
}