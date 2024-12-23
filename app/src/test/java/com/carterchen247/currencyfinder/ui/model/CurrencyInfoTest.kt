package com.carterchen247.currencyfinder.ui.model

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyInfoTest {

    @Test
    fun `crypto CurrencyData to CurrencyInfo`() {
        val currencyData = CurrencyData(
            id = "CRO",
            name = "Crypto.com Chain",
            symbol = "CRO",
            type = CurrencyType.CRYPTO,
        )
        assertEquals(
            CurrencyInfo(
                avatarCode = "C",
                name = "Crypto.com Chain",
                displayCode = "CRO",
            ),
            currencyData.toCurrencyInfo()
        )
    }

    @Test
    fun `fiat CurrencyData to CurrencyInfo`() {
        val currencyData = CurrencyData(
            id = "GBP",
            name = "British Pound",
            symbol = "£",
            code = "GBP",
            type = CurrencyType.FIAT,
        )
        assertEquals(
            CurrencyInfo(
                avatarCode = "G",
                name = "British Pound",
                displayCode = "GBP",
            ),
            currencyData.toCurrencyInfo()
        )
    }
}