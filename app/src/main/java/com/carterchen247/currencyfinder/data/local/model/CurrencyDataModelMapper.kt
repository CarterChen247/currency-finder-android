package com.carterchen247.currencyfinder.data.local.model

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

fun CurrencyDataModel.toCurrencyData(currencyType: CurrencyType) = CurrencyData(
    id = id,
    name = name,
    symbol = symbol,
    code = code.orEmpty(),
    type = currencyType,
)