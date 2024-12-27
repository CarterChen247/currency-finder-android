package com.carterchen247.currencyfinder.util.resource

import androidx.test.core.app.ApplicationProvider
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class CurrencyResourceProviderImplTest {

    private lateinit var currencyResourceProvider: CurrencyResourceProviderImpl
    private val gson = Gson()

    @Before
    fun setUp() {
        currencyResourceProvider = CurrencyResourceProviderImpl(ApplicationProvider.getApplicationContext(), gson)
    }

    @Test
    fun `getDataset should return correct dataset for CurrencyType == CRYPTO`() {
        val dataset = currencyResourceProvider.getDataset(CurrencyType.CRYPTO)

        assertEquals(14, dataset.size)
        assertEquals(
            CurrencyData(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC",
                type = CurrencyType.CRYPTO,
            ),
            dataset.first()
        )
        assertEquals(
            CurrencyData(
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC",
                type = CurrencyType.CRYPTO,
            ),
            dataset.last()
        )
    }

    @Test
    fun `getDataset should return correct dataset for CurrencyType == FIAT`() {
        val dataset = currencyResourceProvider.getDataset(CurrencyType.FIAT)

        assertEquals(7, dataset.size)
        assertEquals(
            CurrencyData(
                id = "SGD",
                name = "Singapore Dollar",
                symbol = "$",
                code = "SGD",
                type = CurrencyType.FIAT,
            ),
            dataset.first()
        )
        assertEquals(
            CurrencyData(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
                code = "USD",
                type = CurrencyType.FIAT,
            ),
            dataset.last()
        )
    }
}
