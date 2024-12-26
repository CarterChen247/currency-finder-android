package com.carterchen247.currencyfinder

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

object TestData {
    val currencyData = CurrencyData(
        id = "CRO",
        name = "Crypto.com Chain",
        symbol = "CRO",
        type = CurrencyType.CRYPTO,
    )
}
